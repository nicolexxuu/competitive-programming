#include <iostream>
#include <vector>
#define MAXN 100003

using namespace std;

int N, M;
bool mark[MAXN];
vector<int> adj[MAXN];

pair<int, int> dfs (int node) {
	if(mark[node]) return {0, 0};
	
	mark[node] = true;
	
	pair<int, int> ret = {1, adj[node].size()};
	for(int to: adj[node]) {
		if(mark[to]) continue;
		pair<int, int> add = dfs(to);
		ret.first += add.first;
		ret.second += add.second;
	}
	
	
	return ret;
}

int main() {
	cin >> N >> M;
	for(int i = 0; i < M; i++) {
		int u, v;
		cin >> u >> v;
		u--; v--;
		adj[u].push_back(v);
		adj[v].push_back(u);
	}
	
	long res = 1;
	for(int i = 0; i < N; i++) {
		if(mark[i]) continue;
		pair<int, int> ret = dfs(i);
		int nodes = ret.first, edges =ret.second;
		edges /= 2;
		
		if(nodes == edges) res *= 2;
		else if(nodes > edges) res *= nodes;
		else res *= 0;
		// cout << "nodes" << nodes << " edges " << edges << endl;
		
		res %= 1000000007;
	}
	
	cout << res << endl;
}
