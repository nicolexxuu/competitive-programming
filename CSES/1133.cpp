#include <bits/stdc++.h>
#define ll long long

using namespace std;

const int MXN = 200005;
vector<int> adj[MXN];
ll sub[MXN], dist[MXN];
int n;

void dfs(int u, int p) {
	sub[u] = 1;
	for(int to : adj[u]) {
		if(to == p) continue;
		dfs(to, u);
		sub[u] += sub[to];
		dist[u] += dist[to] + sub[to];
	}
}

void dfs2(int u, int p) {	
	for(int to : adj[u]) {
		if(to == p) continue;
		dist[to] = dist[u] + (n - 2*sub[to]);
		dfs2(to, u);
	}
}

int main() {
	cin.tie(0)->sync_with_stdio(0);
	cin >> n;
	
	for(int i = 0; i < n-1; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	
	dfs(0, -1);
	dfs2(0, -1);
	
	for(int i = 0; i < n; i++) cout << dist[i] << " ";
}