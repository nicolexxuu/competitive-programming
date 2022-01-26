// Dijkstra's Algorithm Implementation
// O(E log V) Approach

#include <bits/stdc++.h>
#define ll long long
using namespace std;

const int MAXN = 100005;
vector<pair<int, int>> adj[MAXN];
ll dist[MAXN];

int main() {
	int N, M;
	cin >> N >> M;
	for(int i = 0; i < M; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		adj[a-1].push_back({b-1, c});
		adj[b-1].push_back({a-1, c});
	}
	
	for(int i = 0; i < N; i++) dist[i] = LLONG_MAX;
	dist[0] = 0;
	priority_queue<pair<ll, int>, vector<pair<ll, int>>, greater<pair<ll, int>>> toVisit;	
	toVisit.push({0, 0});
	
	while(toVisit.size()) {
		int id;
		ll cdist;
		tie(cdist, id) = toVisit.top();
		toVisit.pop();
		
		if(cdist != dist[id]) continue;
		
		for(pair<int, int> e : adj[id]) {
			if(cdist + e.second < dist[e.first]) {
				dist[e.first] = cdist + e.second;
				toVisit.push({dist[e.first], e.first});
			}
		}
	}
	
}

