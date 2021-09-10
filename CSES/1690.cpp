#include <bits/stdc++.h>
using namespace std;

const int MOD = 1e9+7;
vector<int> from[20];
int dp[1<<20][20];

int main() {
	
	int n, m;
	cin >> n >> m;
	for(int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		from[b].push_back(a);
	}
	
	dp[1][0] = 1;
	for(int curr = 3; curr < 1 << n; curr++) {
		if((curr & 1) == 0) continue;
		if(curr != (1 << n) - 1 && (curr & (1 << (n - 1)))) continue;
		for(int last = 1; last < n; last++) {
			if((curr & (1 << last)) == 0) continue;
			int prev = curr - (1 << last);
			for(int i : from[last]) {
				if(curr & (1 << i)) {
					dp[curr][last] += dp[prev][i];
					dp[curr][last] %= MOD;
				}
			}
		}
	}
	
	cout << dp[(1 << n) - 1][n-1] << '\n';
	
}
