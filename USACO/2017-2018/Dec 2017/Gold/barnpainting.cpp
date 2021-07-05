#include <iostream>
#include <algorithm>
#include <vector> // move exlipose file
#define MOD 1000000007

using namespace std;

vector<int> adj[100000];
int colored[100000];
int N, K;
long dp[100000][3];

long solve(int n, int c, int p) {
	if(colored[n] != -1 && colored[n] != c) dp[n][c] = 0;
	if (dp[n][c] != -1) return dp[n][c];
	
	dp[n][c] = 1;
	
	for(int child: adj[n]) {
		if(child == p) continue;
		long ways = 0;
		for(int color = 0; color < 3; color++) {
			if(color != c) ways += solve(child, color, n);
			ways %= MOD;
		}
		dp[n][c] *= ways;
		dp[n][c] %= MOD;

	return dp[n][c];
}

int main () {
	cin >> N >> K;
	for(int i = 0; i < N-1; i++) {
		int x, y;
		cin >> x >> y;
		x--; y--;
		adj[x].push_back(y);
		adj[y].push_back(x);
	}
	
	for(int i = 0; i < N; i++) {
		colored[i] = -1;
		for(int j = 0; j < 3; j++) dp[i][j] = -1;
	}
	
	for(int i = 0; i < K; i++) {
		int b, c;
		cin >> b >> c;
		colored[b-1] = c-1;
	}

	cout << (solve(0, 0, -1) + solve(0, 1, -1) + solve(0, 2, -1))%MOD << endl;
}

