#include <bits/stdc++.h>

using namespace std;

long long par[2501], dist[2501];
struct Edge { int a, b, c; };

int main() {
	int n, m;
	cin >> n >> m;
	for(int i = 0; i < n; i++)  dist[i] = 2500000000000;
	
	vector<Edge> edges;
	for(int i = 0; i < m; i++) {
		int a, b, c; 
		cin >> a >> b >> c;
		edges.push_back({a, b, c});
	}
	
	dist[1] = 0;
	int last;
	for(int i = 1; i <= n; i++) {
		last = -1;
		for(Edge e : edges) {
			if(dist[e.a] + e.c < dist[e.b]) {
				dist[e.b] = dist[e.a] + e.c;
				par[e.b] = e.a;
				last = e.b;
			}
		}
	}
	
	if(last == -1) {
		cout << "NO" << endl;
	}  else {
		cout << "YES" << endl;
		for(int i = 0; i < n; i++) last = par[last];
		vector<int> cycle;
		int curr = last;
		while(cycle.size() == 0 || curr != last) {
			cycle.push_back(curr);
			curr = par[curr];
		}
		
		cycle.push_back(last);
		reverse(cycle.begin(), cycle.end());
		
		for(int i : cycle) cout << i << " ";
	}
}
