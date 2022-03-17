#include <bits/stdc++.h> 

using namespace std;

vector<int> adj[100000];
int team[100000];

bool dfs(int node, int id) {
	team[node] = id;
	
	bool res = true;
	for(int to : adj[node]) {
		if(team[to] == 0) res &= dfs(to, 3 - id);
		else if(team[to] == id) res = false;
	}
	return res;
}

int main() {
	int n, m; cin >> n >> m;
	for(int i = 0; i < m; i++) {
		int a, b; cin >> a >> b;
		a--; b--;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	
	bool res = true;
	for(int i = 0; i < n; i++) 
		if(team[i] == 0) res &= dfs(i, 1);
	
	if(res) 
		for(int i = 0; i < n; i++) cout << team[i] << " ";
	else
		cout << "IMPOSSIBLE" << endl;
}
