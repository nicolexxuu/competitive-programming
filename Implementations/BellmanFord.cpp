// Bellman-Ford Implementation
// O(NM)
#include <bits/stdc++.h>
#define MAXN 2501
using namespace std;

int dist[MAXN];

struct Edge { int a, b, c; };

int main() {
	int n, m;
	cin >> n >> m;
	for(int i = 0; i < n; i++) dist[i] = INT_MAX;
	
	vector<Edge> edges;
	for(int i = 0; i < m; i++) {
		int a, b, c; 
		cin >> a >> b >> c;
		edges.push_back({a, b, c});
	}
	
	dist[1] = 0;
	int last;
	for(int i = 1; i <= n-1; i++) {
		last = -1;
		for(Edge e : edges)
			if(dist[e.a] + e.c < dist[e.b]) dist[e.b] = dist[e.a] + e.c;
	}
	
	// check for negative cycles
	bool cycle = false;
	for(Edge e : edges) {
		if(dist[e.a] + e.c < dist[e.b]) {
			cycle = true;
			break; 
		}
	}
}
