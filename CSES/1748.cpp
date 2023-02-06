// Binary Index Tree Implementation

#include <bits/stdc++.h>
#define ll long long

using namespace std;

const int MAXN = 200005;
const int MOD = 1e9+7;

ll BIT[MAXN]; 
vector<ll> x, ind;

void add(int idx, ll val) {
	while(idx < MAXN) {
		BIT[idx] = (BIT[idx] + val) % MOD;
		idx += idx & -idx;
	}
}

int get(int idx) {
	ll sum = 0;
	while(idx > 0) {
		sum = (sum + BIT[idx]) % MOD;
		idx -= idx & -idx;
	}
	
	return sum;
}

int get_ci(int a) {
	return lower_bound(ind.begin(), ind.end(), a) - ind.begin();
}

int main() {
	int n; cin >> n;
	for(int i = 0; i < n; i++) {
		int h; cin >> h;
		x.push_back(h);
		ind.push_back(h);
	}
	
	sort(ind.begin(), ind.end());
	ind.erase(unique(ind.begin(), ind.end()), ind.end());
	
	for(int i = 0; i < n; i++) 
		add(get_ci(x[i])+1, 1+get(get_ci(x[i])));
	
	cout << get(n) << endl;
}
