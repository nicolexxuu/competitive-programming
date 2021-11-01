#include <bits/stdc++.h>
#define MX 200

using namespace std;

int dp[101][101][101];
int seq[101];

int main() {
	ifstream cin("taming.in");
	ofstream cout("taming.out");
	
	int N; cin >> N;
	for(int i = 1; i <= N; i++) cin >> seq[i];
	
	for(int day = 0; day <= N; day++) {
		for(int b = 0; b <= N; b++) {
			for(int last = 0; last <= N; last++) {
				dp[day][b][last] = MX;
			}
		}
	}
	
	dp[1][1][1] = seq[1] != 0;
	for(int day = 2; day <= N; day++) {
		for(int b = 1; b <= day; b++) {
			// no breakout on day
			for(int last = b; last < day; last++)
				dp[day][b][last] = dp[day-1][b][last] + (seq[day] != day - last);
			
			// breakout on day
			for(int prev = b-1; prev < day; prev++)
				dp[day][b][day] = min(dp[day][b][day], dp[day-1][b-1][prev] + (seq[day] != 0));
		}
	}
	
	for(int b = 1; b <= N; b++) {
		int res = MX;
		for(int last = b; last <= N; last++) res = min(res, dp[N][b][last]);
		cout << res << endl;
	}
}
