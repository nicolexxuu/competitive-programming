#include <bits/stdc++.h>
#define ll long long
using namespace std;

int MOD = 1e9+7;
ll dp[100000];

int main() {
	int n; cin >> n;
	int g = n * (n + 1) / 2;
	if(g % 2) {
		cout << 0 << endl;
	} else {
		dp[0] = 1;
		for(int c = 1; c < n; c++)
			for(int i = g/2; i >= c; i--)
				(dp[i] += dp[i-c]) %= MOD;
		
		cout << dp[g/2] << endl;
	}
}
