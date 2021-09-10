#include <bits/stdc++.h>

using namespace std;

vector<int> adj[200000];
int dp[200000][2];

void dfs(int node, int parent) {
	for(int to : adj[node]) { // dp[node][0]: no edges leading into child nodes
		if(to != parent) {
			dfs(to, node);
			dp[node][0] += max(dp[to][0], dp[to][1]);
		}
	}
	
	for(int to : adj[node]) { // dp[node][1]: one edge leading into a child node
		if(to != parent) {
			dp[node][1] = max(dp[node][1], dp[to][0] + 1 + dp[node][0] - max(dp[to][0], dp[to][1]));
		}
	}
}

int main() {
	int n; cin >> n;
	
	for(int i = 0; i < n-1; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	
	dfs(0, -1);
	
	cout << max(dp[0][0], dp[0][1]) << '\n';
}
