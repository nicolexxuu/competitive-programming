#include <bits/stdc++.h>

using namespace std;
int dp[5001][5001];

int main() {
	string s1, s2;
	cin >> s1 >> s2;
	int n = s1.size(), m = s2.size();
	
	for(int i = 0; i <= n; i++)
		for(int j = 0; j <= m; j++)
			dp[i][j] = 1e9;
			
	dp[0][0] = 0;
	for(int i = 0; i <= n; i++) {
		for(int j = 0; j <= m; j++) {
			if(i) dp[i][j] = min(dp[i][j], dp[i-1][j] + 1); // remove
			if(j) dp[i][j] = min(dp[i][j], dp[i][j-1] + 1); // add
			if(i && j) dp[i][j] = min(dp[i][j], dp[i-1][j-1] + (s1[i-1] != s2[j-1]));
		}
	}
	
	cout << dp[n][m] << endl;
}
