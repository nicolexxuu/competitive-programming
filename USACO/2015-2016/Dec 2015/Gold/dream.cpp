#include <iostream>
#include <queue>
#define MAXN 1003
#define INF 2000000000
using namespace std;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, 1, 0, -1};
int N, M;
int grid[MAXN][MAXN], final[MAXN][MAXN][2];

struct State {  //TODO: familiarize?
	int r, c, smell, cost;
	State (int r, int c, int smell, int cost) :
		 r(r), c(c), smell(smell), cost(cost) {}
	
	bool operator < (const State &other) const {
		return cost > other.cost;
	}
};

int main () {
	cin >> N >> M;
	for(int i = 0; i < N; i++) {
		for(int j = 0; j < M; j++) {
			final[i][j][0] = INF;
			final[i][j][1] = INF;
			cin >> grid[i][j];
		}
	}
	
	priority_queue<State> to_visit;
	to_visit.push( State(0, 0, 0, 0) );
	
	while(!to_visit.empty()) {
		State curr = to_visit.top();
		to_visit.pop();
		int r = curr.r, c = curr.c, smell = curr.smell, cost = curr.cost;
		if(final[r][c][smell] != INF) continue;
		final[r][c][smell] = cost;
		
		if(r == N-1 && c == M-1) {
			cout << cost << endl;
			return 0;
		}
		
		for(int k = 0; k < 4; k++) {
			int ns = smell, nr = r, nc = c;
			int traveled = 0;
			
			bool finish = false;
			while(!finish || grid[nr][nc] == 4) {
				finish = true;
				if(grid[nr][nc] == 4) ns = 0;
				if(grid[nr][nc] == 2) ns = 1;
				
				int nnr = nr + dr[k], nnc = nc + dc[k];
				if(nnr < 0 || nnr >= N || nnc < 0 || nnc >= M || grid[nnr][nnc] == 0 || (grid[nnr][nnc] == 3 && ns == 0)) break;
				nr = nnr, nc = nnc;
				traveled++;
			}
			if(final[nr][nc][ns] > cost + traveled) to_visit.push(State(nr, nc, ns, cost + traveled));
		}
	}
	
	cout << -1 << endl;
}
