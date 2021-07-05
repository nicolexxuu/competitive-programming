#include <iostream>
#include <algorithm>
#include <queue>
#include <string.h>

using namespace std;

int dr[]{-1, 0, 1, 0}, dc[]{0, 1, 0, -1};
int grid[505][505];
bool visited[505][505];
vector<int> waypts;

int main () {
	int M, N;
	cin >> M >> N;
	for(int i = 0; i < M; i++) { 
		for(int j = 0; j < N; j++) {
			cin >> grid[i][j];
		}
	}
	for(int i = 0; i < M; i++) {
		for(int j = 0; j < N; j++) {
			int a;
			cin >> a;
			if(a) waypts.push_back(N*i + j);
		}	
	}	
	
	int lo = 0, hi = 1000000000;
	while(lo < hi) {
		int mid = (lo+hi)/2;
		//cout << mid << endl;
		memset(visited, false, sizeof visited);
		
		queue<int> toVisit;
		toVisit.push(0);
		
		while(!toVisit.empty()) {
			int curr = toVisit.front();
			int r = curr/N, c = curr%N;
			toVisit.pop();
			
			if(visited[r][c]) continue;
			visited[r][c] = true;
			
			for(int k = 0; k < 4; k++) {
				int nr = r + dr[k], nc = c + dc[k];
				if(nr < 0 || nr >= M || nc < 0 || nc >= N || abs(grid[r][c] - grid[nr][nc]) > mid) continue;
				toVisit.push(nr*N+nc);
			}
			
		}
		bool valid = true;
		for(int i: waypts) if(!visited[i/N][i%N]) valid = false;
		
		if(valid) hi = mid;
		else lo = mid + 1;
	}
	cout << lo << endl;
}
