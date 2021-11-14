#include <bits/stdc++.h>

using namespace std;
int coins[100], dp[1000001];

int main() {
	int n, x;
	cin >> n >> x;
	
	for(int i = 0; i < n; i++) cin >> coins[i];
	
	dp[0] = 1;
	for(int s = 1; s <= x; s++) {
		for(int i = 0; i < n; i++) {
			if(s >= coins[i]) {
				dp[s] += dp[s-coins[i]];
				dp[s] %= 1000000007;
			}
		}
	}
	
	cout << dp[x] << endl;
}
