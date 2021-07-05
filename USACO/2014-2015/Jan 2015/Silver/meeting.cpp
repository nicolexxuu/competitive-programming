#include <iostream>
#include <vector>

using namespace std;

struct Edge {
	int from, first, second;
	
	Edge (int f, int ff, int s) {
		from = f; first = ff; second = s; 
	}
};

vector<Edge> adj[100];
int N, M;

int dp1[100][10001], dp2[100][10001];

int main () {
	cin >> N >> M;
	for(int i = 0; i < M; i++) {
		int a, b, c, d;
		cin >> a >> b >> c >> d;
		a--; b--;
		adj[b].push_back(Edge(a, c, d));
	}
	
	dp1[0][0] = true;
	dp2[0][0] = true;
	
	for(int i = 1; i < N; i++) {
		for(Edge e: adj[i]) {
			for(int time = 1; time <= N*100; time++) {
				if(e.first <= time) dp1[i][time] |= dp1[e.from][time - e.first];
				if(e.second <= time) dp2[i][time] |= dp2[e.from][time - e.second];
			}
		}
	}
	
	bool found = false;
	for(int i = 1; i < 10001; i++) {
		if(dp1[N-1][i] && dp2[N-1][i]) {
			cout << i << endl;
			found = true;
			break;
		}
	}
	
	if(!found) cout << "IMPOSSIBLE" << endl;
}
