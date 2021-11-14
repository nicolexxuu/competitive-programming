#include <bits/stdc++.h>

using namespace std;

const int MX = 2e5+3;
int BIT[MX], last[MX];

void upd(int idx, int add) {
	while(idx < MX) {
		BIT[idx] += add;
		idx += idx & -idx;
	}
}

int get(int idx) {
	int res = 0;
	while(idx > 0) {
		res += BIT[idx];
		idx -= idx & -idx;
	}
	return res;
}

int main() {
	int N; cin >> N;
	long long ans = 0;
	
	for(int i = 1; i <= N; i++) {
		int b; cin >> b;
		if(last[b] != 0) upd(last[b], -1);
		ans += get(i) - get(last[b]);
		last[b] = i;
		upd(i, 1);
	}
	
	cout << ans << endl;
}


