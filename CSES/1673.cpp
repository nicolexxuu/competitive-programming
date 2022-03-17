#include <bits/stdc++.h>
#define MAXN 2501
#define ll long long
#define pii pair<int, int>
#define f first
#define s second
using namespace std;

vector<pii> adj[MAXN], adj2[MAXN];
ll dist[MAXN];
bool connected[MAXN], connected2[MAXN];

void dfs(int node) {
	if(connected[node]) return;
	connected[node] = true;
	for(auto &e : adj[node]) 
		dfs(e.f);
}

void dfs2(int node) {
	if(connected2[node]) return;
	connected2[node] = true;
	for(auto& e : adj2[node]) 
		dfs2(e.f);
}

int main() {
	int n, m;
	cin >> n >> m;
	for(int i = 1; i <= n; i++) dist[i] = -1e13;
	
	for(int i = 0; i < m; i++) {
		int a, b, c; 
		cin >> a >> b >> c;
		adj[a].push_back({b, c});
		adj2[b].push_back({a, c});
	}
	
	dfs(1);
	dfs2(n);
	
	dist[1] = 0;
	for(int i = 1; i <= n-1; i++)
		for(int a = 1; a <= n; a++) 
			for(auto& e : adj[a]) if(dist[a] + e.s > dist[e.f]) dist[e.f] = dist[a] + e.s;
	
	ll res = dist[n];
	for(int a = 1; a <= n; a++) 
		for(auto& e : adj[a])
			if(dist[a] + e.s > dist[e.f] && connected[a] && connected2[a]) 
				res = -1;
				
	cout << res << endl;
}


