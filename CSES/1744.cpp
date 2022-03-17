#include <bits/stdc++.h>

using namespace std;
int dp[501][501];

int main() {
	int a, b;
	cin >> a >> b;
	
	for(int i = 1; i <= a; i++) {
		for(int j = 1; j <= b; j++) {
			dp[i][j] = (i == j) ? 0 : 1e9;
			for(int s = 1; s < max(i, j); s++) {
				if(i >= s) dp[i][j] = min(dp[i][j], dp[s][j] + dp[i-s][j] + 1);
				if(j >= s) dp[i][j] = min(dp[i][j], dp[i][s] + dp[i][j-s] + 1);
			}
		}
	}
	
	cout << dp[a][b] << endl;
}
