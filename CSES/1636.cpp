#include <bits/stdc++.h>

using namespace std;

int dp[1000001];

int main() {
	int n, x;
	cin >> n >> x;
	
	dp[0] = 1;
	for(int i = 0; i < n; i++) {
		int c; cin >> c;
		for(int j = c; j <= x; j++) {
			dp[j] += dp[j-c];
			dp[j] %= 1000000007;
		}
	}
	
	cout << dp[x] << endl;
}
