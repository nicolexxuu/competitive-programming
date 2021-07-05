#include <iostream>
#include <vector>
#include <algorithm>
#define pii pair<int, int>

using namespace std;

int dist[220];
vector<pii> adj[220];

int main () {
	int D, P, C, F, S;
	cin >> D >> P >> C >> F >> S;
	
	for(int i = 0; i < P; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		adj[a].push_back({b, -D});
	}
	
	for(int i = 0; i < F; i++) {
		int a, b, t;
		cin >> a >> b >> t;
		a--; b--;
		adj[a].push_back({b, t-D});
	}
		
	for(int i = 0; i < C; i++) dist[i] = 2000000;
	dist[S-1] = 0;
	
	for(int i = 0; i < C-1; i++) {
		for(int from  = 0; from < C; from++) {
			for(int to = 0; to < adj[from].size(); to++) {
				pii p = adj[from][to];
				if(dist[p.first] > dist[from] + p.second) {
					dist[p.first] = dist[from] + p.second;
				}
			}
		}
	}
	
	bool cycle = false;
	for(int from  = 0; from < C; from++) {
		for(pii p: adj[from]) {
			if(dist[p.first] > dist[from] + p.second) cycle = true;
		}
	}

	int res;
	if(cycle) res = -1;
	else res = D - *min_element(dist, dist + C);
	cout << res << endl;
}
