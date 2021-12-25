#include <bits/stdc++.h>

using namespace std;
int dp[501][501];
int c[501];

int main() {
	int n; cin >> n;
	for(int i = 0; i < n; i++) cin >> c[i];
	for(int i = 0; i < n; i++) {
		dp[i][i] = 1;
		dp[i][i+1] = 2 - (c[i] == c[i+1]);
	}
	
	for(int len = 3; len <= n; len++) {
		for(int r = len - 1; r < n; r++) {
			int l = r - len + 1;
			dp[l][r] = 500;
			for(int i = l; i < r; i++) dp[l][r] = min(dp[l][r], dp[l][i] + dp[i+1][r]);
			
			for(int pal = 0; pal < (r - l) / 2 && c[l + pal] == c[r - pal]; pal++)
				dp[l][r] = min(dp[l][r], dp[l + pal + 1][r - pal - 1]);
		}
	}
	
	cout << dp[0][n-1] << endl;
}
