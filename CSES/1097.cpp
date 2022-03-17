#include <bits/stdc++.h>
#define ll long long
using namespace std;

ll x[5001], ps[5001];
ll dp[5001][5001];

int main() {
	int n; cin >> n;
	for(int i = 1; i <= n; i++) {
		cin >> x[i];
		ps[i] = ps[i-1] + x[i];
		dp[i][i] = x[i];
	}
	
	for(int len = 2; len <= n; len++) {
		for(int l = 1; l <= n - len + 1; l++) {
			int r = l + len - 1;
			dp[l][r] = max(ps[r] - ps[l] - dp[l+1][r] + x[l], ps[r-1] - ps[l-1] - dp[l][r-1] + x[r]);
		}
	}
	
	cout << dp[1][n] << '\n';
}
