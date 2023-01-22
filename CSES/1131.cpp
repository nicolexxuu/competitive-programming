#include <bits/stdc++.h>

using namespace std;

const int MXN = 200005;
vector<int> adj[MXN];

int last, dist;

void dfs(int node, int par, int depth) {
	if(depth > dist) {
		dist = depth;
		last = node;
	}
		
	for(int to : adj[node]) {
		if(to == par) continue;
		dfs(to, node, depth+1);
	}
}

int main() {
	cin.tie(0)->sync_with_stdio(0);
	int n; cin >> n;
	
	for(int i = 0; i < n-1; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	
	dfs(0, -1, 0);
	
	dist = 0;
	dfs(last, -1, 0);
	
	cout << dist << endl;
}
