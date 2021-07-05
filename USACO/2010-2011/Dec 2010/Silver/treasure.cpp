#include <iostream>
#include <algorithm>
#define MAXN 5005

using namespace std;
int N, dp[MAXN], ps[MAXN], coins[MAXN];

int main () {
	cin >> N;
	for(int i = 1; i <= N; i++) {
		cin >> coins[i];
		ps[i] = ps[i-1] + coins[i];
	}
	
	for(int l = N; l >= 1; l--) {
		for(int r = l; r <= N; r++) 
			dp[r] = ps[r] - ps[l-1] - min(dp[r], dp[r-1]);
	}
	cout << dp[N] << endl;
}
