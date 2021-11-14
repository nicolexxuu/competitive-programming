#include <bits/stdc++.h>

using namespace std;
int h[1001], s[1001];
int dp[1001][100001];

int main() {
	int n, x;
	cin >> n >> x;
	
	for(int i = 1; i <= n; i++) cin >> h[i];
	for(int i = 1; i <= n; i++) cin >> s[i];
	
	int count = 0;
	for(int b = 1; b <= n; b++) {
		for(int p = 1; p <= x; p++) {
			dp[b][p] = dp[b-1][p];
			if(p >= h[b]) dp[b][p] = max(dp[b-1][p-h[b]]+s[b], dp[b][p]);
		}
	}
	
	cout << dp[n][x];
	
}
