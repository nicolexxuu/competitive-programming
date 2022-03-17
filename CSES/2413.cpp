#include <bits/stdc++.h>
#define f first
#define s second
#define ll long long
#define MAXN 1000000

using namespace std;
ll MOD = 1e9+7;

int main() {
	cin.tie(0)->sync_with_stdio(0);
	int t; cin >> t;
	
	vector<pair<ll, ll>> dp(MAXN+1);
	dp[1].f = dp[1].s = 1;
	for(int i = 2; i <= MAXN; i++) {
		dp[i].f = (dp[i-1].f * 4 + dp[i-1].s) % MOD;
		dp[i].s = (dp[i-1].f + dp[i-1].s * 2) % MOD;
	}
		
	while(t--) {
		int n; cin >> n;
		cout << (dp[n].f + dp[n].s) % MOD << '\n';
	}
}

