#include <bits/stdc++.h>
#define MAXN 501

using namespace std;

int N;
int dr[4] = {-1, 0, 1, 0}, dc[4] = {0, 1, 0, -1};
int grid[MAXN][MAXN];
int rt[MAXN*MAXN], sz[MAXN*MAXN];
vector<pair<int, int>> adj[1000001];

int find(int a) {
	while(rt[a] != a) {
		rt[a] = rt[rt[a]];
		a = rt[a];
	}
	
	return a;
}

void merge(int a, int b) {
	int r_a = find(a), r_b = find(b);
	if(r_a == r_b) return;
	if(r_a < r_b) swap(r_a, r_b);
	rt[r_b] = r_a;
	sz[r_a] += sz[r_b];
}

int main() {
	//ifstream cin("tractor.in");
	//ofstream cout("tractor.out");
	cin >> N;
	for(int i = 0; i < N; i++) {
		for(int j = 0; j < N; j++) {
			cin >> grid[i][j];
			rt[i*N+j] = i*N+j;
			sz[i*N+j] = 1;
		}
	}
	
	for(int i = 0; i < N; i++) {
		for(int j = 0; j < N; j++) {
			for(int k = 0; k < 4; k++) {
				int nr = i + dr[k], nc = j + dc[k];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				int a = i*N+j, b = nr*N+nc;
				adj[abs(grid[i][j] - grid[nr][nc])].push_back({a, b});
			}
		}
	}
	
	for(int cost = 0; cost <= 1000000; cost++) {
		for(pair<int, int> pii : adj[cost]) {
			merge(pii.first, pii.second);
			if(sz[find(pii.first)] >= (N*N+1)/2) {
				cout << cost << endl;
				return 0;
			}
		}
	}
	
}
