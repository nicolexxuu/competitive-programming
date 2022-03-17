#include <bits/stdc++.h>

using namespace std;
const int INF = 1e9+1;
int x[200000];

int main() {
	int n; cin >> n;
	vector<int> dp(n+1);
	fill(dp.begin(), dp.end(), INF);
	dp[0] = -INF;
	
	for(int i = 0; i < n; i++) {
		int x; cin >> x;
		dp[lower_bound(dp.begin(), dp.end(), x) - dp.begin()] = x;
	}
	
	for(int i = n; i > 0; i--) {
		if(dp[i] < INF) {
			cout << i << '\n';
			break;
		}
	}
}
