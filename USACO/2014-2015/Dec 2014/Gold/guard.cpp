#include <bits/stdc++.h>
#define ll long long
using namespace std;

int cows[20][3]; 
int dp[1<<20][2]; // 0 - height, 1 - strength
int main() {
	ifstream cin("guard.in");
	ofstream cout("guard.out");
	int N, H;
	cin >> N >> H;
	
	for(int i = 0; i < N; i++) {
		cin >> cows[i][0] >> cows[i][1] >> cows[i][2]; // height, weight, strength
		dp[(1<<i)][0] = cows[i][0];
		dp[(1<<i)][1] = cows[i][2];
	}
	
	int safety = -1;
	for(int mask = 0; mask < (1<<N); mask++) {
		if(__builtin_popcount(mask) < 2) continue;
		for(int top = 0; top < N; top++) {
			if(!(mask & (1<<top))) continue;
			int before = mask - (1<<top);
			if(dp[before][1] - cows[top][1] > dp[mask][1]) {
				dp[mask][0] = dp[before][0] + cows[top][0];
				dp[mask][1] = min(dp[before][1] - cows[top][1], cows[top][2]);
			}
			
			if(dp[mask][0] >= H) safety = max(safety, dp[mask][1]);
		}
	}
	
	if(safety < 0) cout << "Mark is too tall" << endl;
	else cout << safety << endl;
}
