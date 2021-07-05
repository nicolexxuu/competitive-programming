#include <bits/stdc++.h>
#define ll long long
#define plii pair<long, pair<int, int>>

using namespace std;

int N, T;
int grass[100][100];
ll dist[100][100][4];
bool visited[100][100][4];

int dr[4] = {-1, 0, 1, 0};
int dc[4] = {0, 1, 0, -1};

int main() {
	ifstream cin("visitfj.in");
	ofstream cout("visitfj.out");
	cin >> N >> T;
	for(int i = 0; i < N; i++) {
		for(int j = 0; j < N; j++) {
			cin >> grass[i][j];
			for(int k = 1; k <= 4; k++) dist[i][j][k] = LONG_MAX;
		}
	}
	
	priority_queue<plii, vector<plii>, greater<plii>> pq;
	pq.push(make_pair(0, make_pair(0, 0)));
	
	while(!pq.empty()) {
		int r = pq.top().second.first/N, c = pq.top().second.first%N, t = pq.top().second.second;
		pq.pop();
		
		if(visited[r][c][t]) continue;
		visited[r][c][t] = true;
		int nextTime = t + 1;
		if(nextTime == 4) nextTime = 1;
		
		for(int k = 0; k < 4; k++) {
			int nr = r + dr[k], nc = c + dc[k];
			if(visited[nr][nc][nextTime] || nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			ll newDist = (nextTime == 3) ? dist[r][c][t] + T + grass[nr][nc] : dist[r][c][t] + T;
			if(newDist < dist[nr][nc][nextTime]) {
				dist[nr][nc][nextTime] = newDist;
				pq.push(make_pair(newDist, make_pair(nr * N + nc, nextTime)));
			}
		}
	}
	
	ll res = LONG_MAX;
	for(int i = 1; i <= 3; i++) res = min(res, dist[N-1][N-1][i]);
	cout << res << endl;
}
