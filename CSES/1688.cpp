#include <bits/stdc++.h>
#define MX 200000

using namespace std;

int n, q;
vector<int> adj[MX];
int timer = 0, tin[MX], euler[MX*2];
int segtree[MX*4];

void dfs(int node) {
	tin[node] = timer;
	euler[timer++] = node;
	for(int to : adj[node]) {
		dfs(to);
		euler[timer++] = node;
	}
}

int query(int a, int b) {
	a += timer; b += timer;
	int res = -1;
	while(a <= b) {
		if(a % 2 == 1) {
			if(res == -1) res = segtree[a];
			else res = (tin[res] < tin[segtree[a]]) ? res : segtree[a];
			a++;
		}
		if(b % 2 == 0) {
			if(res == -1) res = segtree[b];
			else res = (tin[res] < tin[segtree[b]]) ? res : segtree[b];
			b--;
		}
		
		a /= 2;
		b /= 2;
	}
	
	return res;
}

int lca(int a, int b) {
	if(tin[a] > tin[b]) swap(a, b);
	return query(tin[a], tin[b]) + 1;
}

int main() {
	cin >> n >> q;
	for(int i = 0; i < n-1; i++) {
		int e;
		cin >> e; e--;
		adj[e].push_back(i+1);
	}
	
	dfs(0);
	for(int i = 0; i < timer; i++) segtree[i+timer] = euler[i];
	for(int i = timer-1; i > 0; i--) {
		int a = segtree[i*2], b = segtree[i*2+1];
		segtree[i] = (tin[a] < tin[b]) ? a : b;
	}
	
	for(int i = 0; i < q; i++) {
		int a, b; cin >> a >> b;
		a--; b--;
		cout << lca(a, b) << endl;
	}
}
