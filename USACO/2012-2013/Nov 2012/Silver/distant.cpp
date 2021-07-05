#include <iostream>
#include <cstdio>
#include <algorithm>
#include <queue>

#define NMAX 40
#define INF 2100000000
using namespace std;

int grid[NMAX][NMAX];

int N, A, B;
int dr[4]{-1, 0, 1, 0};
int dc[4]{0, 1, 0, -1};

struct Node {
	int l, d;
	Node(int l, int d) : l(l), d(d) {}
	
	bool operator<(Node const&other) const {
		return d > other.d;
	}
};

int main() {
	freopen("distant.in", "r", stdin);
	freopen("distant.out", "w", stdout);
	cin >> N >> A >> B;
	
	for(int i = 0; i < N; i++ ) {
		string a;
		cin >> a;
		for(int j = 0; j < N; j++) {
			if(a[j] == '(') grid[i][j] = 1;
		}
	}
	
	int result = 0;
	
	for(int start = 0; start < N*N; start++) {
		bool mark[N][N];
		int dist[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				mark[i][j] = false;
				dist[i][j] = INF;
			}
		}
		priority_queue<Node> toVisit;
		toVisit.push(Node(start, 0));
		dist[start/N][start%N] = 0;
		//cout << "start: " << start << endl;
		
		while(!toVisit.empty()) {
			Node curr = toVisit.top();
			toVisit.pop();
			
			int r = curr.l/N, c = curr.l%N, d = curr.d;
			//cout << r << " " << c << endl;
			
			if(mark[r][c]) continue;
			mark[r][c] = true;
			result = max(result, d);
			
			for(int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || mark[nr][nc]) continue;
				if(grid[nr][nc] == grid[r][c] && d + A < dist[nr][nc]) {
					dist[nr][nc] = d + A;
					toVisit.push(Node(nr*N + nc, d + A));
				} else if (grid[nr][nc] != grid[r][c] && d + B < dist[nr][nc]) {
					dist[nr][nc] = d + B;
					toVisit.push(Node(nr * N + nc, d + B));
				}
			}
		}
	}
	
	cout << result << endl;
}
