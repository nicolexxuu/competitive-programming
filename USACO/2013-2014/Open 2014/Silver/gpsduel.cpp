#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

typedef pair<int, int> pii;

const int MAXN = 100003, INF = 2000000000;
int N, M;
vector<pii> adj1[MAXN], adj2[MAXN], res[MAXN];
int dist[MAXN][3];

int dij (vector<pii> adj[MAXN], int a) {
	for(int i = 0; i < MAXN; i++) dist[i][a] = INF;
	
	priority_queue<pii, vector<pii>, greater<pii>> toVisit; // sorts by first element
	toVisit.push(pii(0, N-1));
	dist[N-1][a] = 0;
	
	while(!toVisit.empty()) {
		int d = toVisit.top().first, id = toVisit.top().second;
		toVisit.pop();
		if(d != dist[id][a]) continue;
		
		for(pii edge: adj[id]) {
			int to = edge.first, weight = edge.second;
			if(weight + d < dist[to][a]) {
				dist[to][a] = weight + d;
				toVisit.push(pii(dist[to][a], to));
			}
		}
	}
	
	return dist[0][2];
}

int main () {
	cin >> N >> M;
	for(int i = 0; i < M; i++) {
		int a, b, p, q;
		cin >> a >> b >> p >> q;
		a--, b--;
		adj1[b].push_back(pii(a, p));
		adj2[b].push_back(pii(a, q));
	}
	
	dij(adj1, 0);
	dij(adj2, 1);
	
//	for(int i = 0; i < N; i++) {
//		for(int j = 0; j < 3; j++) {
//			cout << dist[i][j] << " ";
//		}
//		cout << endl;
//	}
	
	for(int from = 0; from < N; from++) {
		for(int j = 0; j < adj1[from].size(); j++ ) {
			int to = adj1[from][j].first;
			int p = adj1[from][j].second, q = adj2[from][j].second;
			
			int c = 0;
			if(dist[to][0] - dist[from][0] != p) c++;
			if(dist[to][1] - dist[from][1] != q) c++;
			res[from].push_back(pii(to, c));
		}
	}
	
	cout << dij(res, 2) << endl;
}
