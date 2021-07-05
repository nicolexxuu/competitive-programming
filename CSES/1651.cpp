#include <bits/stdc++.h>
#define MAXN 200005
#define ll long long

using namespace std;

ll BIT[MAXN];

void upd(int idx, int val) {
	while(idx < MAXN) {
		BIT[idx] += val;
		idx += idx & -idx;
	}
}

ll query(int idx) {
	ll ret = 0;
	while(idx > 0) {
		ret += BIT[idx];
		idx -= idx & -idx;
	}
	return ret;
}

int main() {
	int n, q;
	cin >> n >> q;
	
	int l = 0;
	for(int i = 1; i <= n; i++) {
		int a; cin >> a;
		upd(i, a - l);
		l = a;
	}
	
	for(int i = 0; i < q; i++) {
		int a; cin >> a;
		if(a == 1) {
			int a, b, u;
			cin >> a >> b >> u;
			upd(a, u); upd(b+1, -u);
		} else {
			int k; cin >> k;
			cout << query(k) << endl;
		}
	}
}
