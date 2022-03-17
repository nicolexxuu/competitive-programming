#include <bits/stdc++.h>
#define MAXN 100001

using namespace std;
const int INF = 1e9;
vector<int> adj[MAXN];
int dist[MAXN], par[MAXN];

int main() {
	int n, m;
	cin >> n >> m;
	
	for(int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	
	for(int i = 0; i <= n; i++) dist[i] = INF;
	par[1] = -1;
	dist[1] = 1;
	deque<int> toVisit;
	toVisit.push_back(1);
	
	while(toVisit.size()) {
		int curr = toVisit.front();
		toVisit.pop_front();
		
		for(int to : adj[curr]) {
			if(dist[to] == INF) {
				dist[to] = dist[curr] + 1;
				par[to] = curr;
				toVisit.push_back(to);
			}
		}
	}
	
	if(dist[n] == INF) {
		cout << "IMPOSSIBLE" << '\n';
	} else {
		cout << dist[n] << '\n';
		vector<int> route;
		int curr = n;
		while(curr != -1) {
			route.push_back(curr);
			curr = par[curr];
		}
		
		for(int i = route.size()-1; i >= 0; i--) cout << route[i] << ' ';
	}
	
}
