#include <iostream>
#include <algorithm>
#define MOD 1000000007

using namespace std;

char grid[500][500];
int dp[500][500][2];

int main() {
	int N; cin >> N;
	
	for(int i = 0; i < N; i++) {
		dp[i][i][0] = 1;
		for(int j = 0; j < N; j++) {
			cin >> grid[i][j];
		}
	}
	
	int num = 0;
	for(int steps = N-2; steps >= 0; steps--) { // steps from corners
		num = 1 - num;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				dp[i][j][num] = 0;
			}
		}
		for(int r = 0; r < N; r++) {
			int c = steps - r;
			if(c < 0) continue;
			for(int r2 = r; r2 < N; r2++) {
				int c2 = N*2 - steps - r2 - 2;
				
				if(c2 >= N || grid[r][c] != grid[r2][c2]) continue;

				dp[r][r2][num] += dp[r][r2][1 - num];
				if(r+1 < N) dp[r][r2][num] += dp[r+1][r2][1 - num];
				dp[r][r2][num] %= MOD;
				if(r2-1 >= 0) dp[r][r2][num] += dp[r][r2-1][1 - num];
				dp[r][r2][num] %= MOD;
				if(r+1 < N && r2 - 1 >= 0) dp[r][r2][num] += dp[r+1][r2-1][1 - num];
				dp[r][r2][num] %= MOD;
			}
		}
	}
	
	cout << dp[0][N-1][num] << endl;
}
