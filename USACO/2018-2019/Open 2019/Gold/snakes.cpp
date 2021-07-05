#include <bits/stdc++.h>

using namespace std;
int dp[400][400], mins[400], snakes[400];

int main() {
	ifstream cin("snakes.in");
	ofstream cout("snakes.out");
	
	int N, K;
	cin >> N >> K;
	int snakes[N];
	for(int i = 0; i < N; i++) cin >> snakes[i];
	
	for(int g = 0; g < N; g++) {
		for(int chg = min(g, K); chg >= 0; chg--) {
			int new_min = INT_MAX;
			
			for(int sz = 0; sz < N; sz++) {
				int wasted = snakes[sz] - snakes[g];
				if(wasted < 0) {
					dp[sz][chg] = 400000001;
					continue;
				}
				
				if(chg == 0) dp[sz][chg] += wasted;
				else {
					if(chg != g) dp[sz][chg] = min(dp[sz][chg] + wasted, mins[chg-1] + wasted);
					else dp[sz][chg] = mins[chg-1] + wasted;
				}
				new_min = min(new_min, dp[sz][chg]);
			}
			
			mins[chg] = new_min;
		}
	}
	
	int res = INT_MAX;
	for(int i = 0; i < min(N, K + 1); i++) res = min(res, mins[i]);
	cout << res << endl;
}
