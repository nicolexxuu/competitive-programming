#include <bits/stdc++.h>
#define pii pair<int, int>
#define pb push_back
#define f first
#define s second

using namespace std;

int N, Q;
bool active[100005], rmv[200005];
int res[100005];
vector<int> adj[100005];
vector<pii> edges;
vector<vector<int>> queries;

void dfs(int i, int val) {
	if(res[i] != -1) return;
	res[i] = val;

	for(int to : adj[i]) {
		dfs(to, val);
	}
}
	
int main() {
	cin >> N >> Q;
	for(int i = 0; i < N; i++) {
		res[i] = -1;
		active[i] = true;
	}
	
	for(int q = 0; q < Q; q++) {
		char t; cin >> t;
		if(t == 'A') {
			int x, y;
			cin >> x >> y; 
			x--; y--;
			edges.pb({x, y});
		} else if(t == 'D') {
			int x;
			cin >> x; x--;
			active[x] = false;
			queries.pb({q, x});
		} else if(t == 'R') {
			int e; cin >> e; e--;
			rmv[e] = true;
			queries.pb({q, edges[e].f, edges[e].s});
		}
		
	}
	
	for(int i = 0; i < edges.size(); i++) {
		if(!rmv[i]) {
			adj[edges[i].f].pb(edges[i].s);
			adj[edges[i].s].pb(edges[i].f);
		}
	}
	
	for(int i = 0; i < N; i++) {
		if(active[i]) dfs(i, Q, -1);
	}
	
	for(int z = queries.size()-1; z >= 0; z--) {
		vector<int> q = queries[z];
		if(q.size() == 2) dfs(q[1], q[0]);
		
		if(q.size() == 3) {
			adj[q[1]].push_back(q[2]);
			adj[q[2]].push_back(q[1]);
			
			if(res[q[2]] != -1) dfs(q[1], q[0]);
			if(res[q[1]] != -1) dfs(q[2], q[0]);
		}
	}
	
	for(int i = 0; i < N; i++) cout << res[i] << endl;
}
