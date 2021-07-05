#include <iostream>
#include <algorithm>
#include <fstream>

using namespace std;

int coins[16];
int dp[1 << 16];

int main() {
	
	int K, N;
	cin >> K >> N;
	
	for(int i = 0; i < K; i++ ) cin >> coins[i];
	
	vector<int> c;
	c.push_back(0);
	for(int i = 1; i <= N; i++ ) {
		int a;
		cin >> a;
		c.push_back(a + c[i-1]);
	}

	int res = -1;
	
	for(int i = 0; i < (1 << K); i++) {
		
		int rem = 0;
		for(int coin = 0; coin < K; coin++) {
			int s = 1 << coin;
			
			if(i & s) {
				int temp = upper_bound(c.begin(), c.end(), c[dp[i ^ s]] + coins[coin]) - c.begin() - 1;
				dp[i] = max(dp[i], temp);
			} else {
				rem += coins[coin];
			}
		}
		
		if(dp[i] == N) res = max(res, rem);
	}
	
	cout << res << endl;
}
