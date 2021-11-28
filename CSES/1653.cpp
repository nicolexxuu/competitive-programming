#include <bits/stdc++.h>

using namespace std;

int dp[1<<20][2];
int w[20];

int main() {
	int n, x;
	cin >> n >> x;
	
	for(int i = 0; i < n; i++) {
		cin >> w[i];
		dp[1<<i][0] = 1, dp[1<<i][1] = w[i];
	}
	
	for(int mask = 0; mask < (1<<n); mask++) {
		if(__builtin_popcount(mask) < 2) continue;
		dp[mask][0] = INT_MAX;
		for(int last = 0; last < n; last++) {
			if(!(mask & (1<<last))) continue;
			auto prev = dp[mask - (1<<last)];
			int weight = prev[1] + w[last];
			int rides = prev[0];
			
			if(weight > x) {
				rides++;
				weight = w[last];
			} 
			
			if(rides < dp[mask][0] || (rides == dp[mask][0] && weight < dp[mask][1])) {
				dp[mask][0] = rides;
				dp[mask][1] = weight;
			}
		}
	}
	
	cout << dp[(1<<n)-1][0];
}
