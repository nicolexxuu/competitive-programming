// Maximum Independent Set - https://judge.yosupo.jp/

#include <bits/stdc++.h>
#define ll long long
using namespace std;

bool adj[40][40]; // 0 - no edge, 1 - edge present
ll comp[40]; // stores bitmask of adj nodes (in complement graph)
ll dp1[1 << 20], dp2[1 << 20];

ll popcount_max(ll m1, ll m2) {
	return __builtin_popcountll(m1) > __builtin_popcountll(m2) ? m1 : m2;
}

int main() {
	int N, M;
	cin >> N >> M;
	int n1 = N/2, n2 = N - n1;
	for(int i = 0; i < M; i++) {
		int u, v;
		cin >> u >> v;
		adj[u][v] = adj[v][u] = 1;
	}
	
	for(int i = 0; i < N; i++)
		for(int j = 0; j < N; j++)
			if(!adj[i][j] && i != j) comp[i] += (1ll << j);
	
	for(ll mask = 1; mask < (1 << n1); mask++) {
		int last = __builtin_ctzll(mask);
		int exc = dp1[mask ^ (1 << last)];
		int inc = dp1[mask & comp[last]] + (1 << last);
		
		dp1[mask] = popcount_max(exc, inc);
	}
	
	for(ll mask = 1; mask < (1 << n2); mask++) {
		int last = __builtin_ctzll(mask);
		int exc = dp2[mask ^ (1 << last)];
		int inc = dp2[mask & (comp[n1+last] >> n1)] + (1 << last);
		dp2[mask] = popcount_max(exc, inc);
	}

	ll res = 0;
	for(ll mask = 0; mask < (1 << n1); mask++) {
		ll merge = 0;
		for(ll i = 0; i < n2; i++) 
			if((comp[n1+i] & mask) == mask) merge += (1 << i);
		res = popcount_max(res, dp1[mask] + (dp2[merge] << n1));
	}
	
	cout << __builtin_popcountll(res) << endl;
	for(int i = 0; i < N; i++)
		if((res & ((ll)(1) << i))) cout << i << " ";
}
