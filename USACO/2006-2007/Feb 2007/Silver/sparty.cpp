#include <iostream>
#include <queue>
#include <algorithm>
#define MAXN 1005

using namespace std;

typedef pair<int, int> pii;

int N, M, X;
int dist1[MAXN], dist2[MAXN];
vector<pii> adj[MAXN], reversed[MAXN];

void dijkstra(int dist[], vector<pii> adj[]);

int main() {
	cin >> N >> M >> X;
	for(int i = 0; i < N; i++) {
		dist1[i] = 2000000000;
		dist2[i] = 2000000000;
	}
	
	for(int i = 0; i < M; i++) {
		int a, b, t;
		cin >> a >> b >> t;
		a--; b--;
		adj[a].push_back(pii(t, b));
		reversed[b].push_back(pii(t, a));
	}

	dijkstra(dist1, adj);
	dijkstra(dist2, reversed);	
	int result = 0;
	for(int i = 0; i < N; i++) {
		result = max(result, dist1[i] + dist2[i]);
	}
	
	cout << result << endl;
}

void dijkstra (int dist[], vector<pii> adj[]) {
	int N = sizeof(dist);
	dist[X-1] = 0;
	priority_queue<pii, vector<pii>, greater<pii>> toVisit;
	toVisit.push(pii(0, X-1));
	while(!toVisit.empty()) {
		pii curr = toVisit.top();
		toVisit.pop();
		int id = curr.second, d = curr.first;
		if(dist[id] != d) continue;
		
		for(pii p: adj[id]) {
			if(d + p.first < dist[p.second]) {
				dist[p.second] = d + p.first;
				toVisit.push(pii(d + p.first, p.second));
			}
		}
	}
}
