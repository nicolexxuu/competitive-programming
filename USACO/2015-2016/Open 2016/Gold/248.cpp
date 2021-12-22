#include <bits/stdc++.h>

using namespace std;

int dp[248][248];

int main() {
	ifstream cin("248.in");
	ofstream cout("248.out");
	
	int N; cin >> N;
	int res = 0;
	for(int i = 0; i < N; i++) {
		cin >> dp[i][i];
		res = max(res, dp[i][i]);
	}
	
	for(int len = 2; len <= N; len++) {
		for(int r = len-1; r < N; r++) {
			int l = r-len+1;
			for(int i = l; i < r; i++)
				if(dp[l][i] == dp[i+1][r] && dp[l][i] != 0) dp[l][r] = max(dp[l][r], dp[l][i]+1);
			res = max(res, dp[l][r]);
		}
	}
	
	cout << res << endl;
}
