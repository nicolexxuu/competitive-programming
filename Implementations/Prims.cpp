#include <bits/stdc++.h>

using namespace std;
const int MAXN = 1005;

bool mark[MAXN];
int dist[MAXN];
int main() {
	int N; cin >> N;
	int adj[N][N];
	
	for(int i = 0; i < N; i++) 
		for(int j = 0; j < N; j++)
			cin >> adj[i][j];
	
	
	long long total = 0;
	for(int i = 0; i < N; i++) dist[i] = INT_MAX;
	dist[0] = 0;
	
	for(int in = 0; in < N; in++) {
		int minDist = INT_MAX;
		int minI = -1;
		
		for(int i = 0; i < N; i++) {
			if(!mark[i] && dist[i] < minDist) {
				minDist = dist[i];
				minI = i;
			}
		}
		
		total += minDist;
		mark[minI] = true;
		for(int i = 0; i < N; i++)
			if(!mark[i]) dist[i] = min(dist[i], adj[minI][i]);
	}
	
	cout << total << endl;
}
