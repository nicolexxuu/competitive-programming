#include <iostream>
#include <algorithm>
#define MAXN 105
#define MAXT 16

using namespace std;

int grid[MAXN][MAXN];
int dp[MAXN][MAXN][MAXT];

int main () {
	int N, M, T;
	int R1, C1, R2, C2;
	cin >> N >> M >> T;
	
	for(int i = 1; i <= N; i++) {
		for(int j = 1; j <= M; j++) {
			char c;
			cin >> c;
			grid[i][j] = c == '*';
		}
	}
	
	cin >> R1 >> C1 >> R2 >> C2;
	
	dp[R1][C1][0] = 1;
	for(int t = 1; t <= T; t++ ) {
		for(int r= 1; r <= N; r++) {
			for(int c = 1; c <= M; c++) {
				if(grid[r][c] == 1) continue;
				dp[r][c][t] = dp[r-1][c][t-1] + dp[r][c-1][t-1] + dp[r+1][c][t-1] + dp[r][c+1][t-1];
			}
		}
	}
	
	
	cout << dp[R2][C2][T] << endl;
}


