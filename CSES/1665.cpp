#include <bits/stdc++.h>
#define ll long long
using namespace std;

const int MAXN = 105;
const ll MOD = 1000000007;
ll dp[2][MAXN][5101];

int main() {
	int n, k;
	cin >> n >> k;
	
	vector<int> a(n+1);
	for(int i = 1; i <= n; i++) cin >> a[i];
	
	sort(a.begin()+1, a.end());
	
//	dp[0][0][0] = 1;
//	int curr = 1;
//	for(int i = 1; i <= n; i++) {
//		memset(dp[curr], 0, sizeof(dp[curr]));
//		for(int j = 0; j < i; j++) {
//			for(int imb = 0; imb <= k; imb++) {
//				int n_imb = imb + j*(a[i] - a[i-1]);
////				if(n_imb > k) continue;
//				ll prev = dp[1 - curr][j][imb];
//				
//				dp[curr][j][n_imb] = (dp[curr][j][n_imb] + prev) % MOD; // make new group of 1
//				dp[curr][j+1][n_imb] = (dp[curr][j+1][n_imb] + prev) % MOD; // open new group
//				if(j) dp[curr][j][n_imb] = (dp[curr][j][n_imb] + prev*j) % MOD; // add to existing group
//				if(j) dp[curr][j-1][n_imb] = (dp[curr][j-1][n_imb] + prev*j) % MOD; 
//			}
//		}
//		
//		curr = 1 - curr;
//	}
	
	dp[1][0][0] = 1, dp[1][1][0] = 1;
	int curr = 0;
	for(int i = 1; i < n; i++) {
		for(int j = 0; j <= i; j++) {
			for(int imb = 0; imb <= k; imb++) {
				dp[curr][j][imb] = 0;
			}
		}
		for(int j = 0; j <= i; j++) {
			for(int imb = 0; imb <= k; imb++) {
				int n_imb = imb + j * (a[i+1] - a[i]);
				ll prev = dp[1-curr][j][imb];
				
				dp[curr][j][n_imb] = (dp[curr][j][n_imb] + prev) % MOD; // make new group of 1
				dp[curr][j+1][n_imb] = (dp[curr][j+1][n_imb] + prev) % MOD; // open new group
				if(j) dp[curr][j][n_imb] = (dp[curr][j][n_imb] + prev*j) % MOD; // add to existing group
				if(j) dp[curr][j-1][n_imb] = (dp[curr][j-1][n_imb] + prev*j) % MOD; // close group
			}
		}
		
		curr = 1 - curr;
	}
	
	ll res = 0;
	for(int i = 0; i <= k; i++) {
		res = (res + dp[1-curr][0][i]) % MOD;
	}
	
	cout << res << endl;
}


/*

*/
