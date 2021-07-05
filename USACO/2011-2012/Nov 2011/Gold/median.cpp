// Above the Median

#include <bits/stdc++.h>

#define MAXN 100005
#define ll long long

using namespace std;

int BIT[MAXN * 2]; // accomodate negative values

void upd(int idx) {
	idx += MAXN;
	while(idx < MAXN * 2) {
		BIT[idx]++;
		idx += idx & -idx;
	}
}

int get(int idx) {
	idx += MAXN;
	int sum = 0;
	while(idx > 0) {
		sum += BIT[idx];
		idx -= idx & -idx;
	}
	
	return sum;
}

int main() {
	int N, X;
	cin >> N >> X;

	int pref[N+1];
	pref[0] = 0;
	
	for(int i = 1; i <= N; i++) {
		int a; cin >> a;
		pref[i] = pref[i-1] + ((a >= X) ? 1 : -1);
	}

	ll res = 0;
	for(int j = 1; j <= N; j++) { // count pairs such that i <= j && P[i-1] <= P[j]
		res += get(pref[j]) + (pref[j] >= 0 ? 1 : 0);
		upd(pref[j]);
	}
	
	cout << res << endl;
}
