#include <iostream>
#include <algorithm>

using namespace std;

int d[1001], dp[1001][1001];
int main() {
	string p;
	cin >> p;
	int N = p.length();
	
	d[0] = 1;
	for(int i = 1; i < N; i++) {
		d[i] = d[i-1] + (p[i] == '(' ? 1 : -1);
	}
	
	dp[0][1] = dp[0][0] = 1;
	for(int i = 1; i < N; i++) {
		for(int j = 0; j < N; j++) {
			int k = d[i] - j;
			if(p[i] == '(') {
				if(j > 0) dp[i][j] += dp[i-1][j-1];
				if(k >= 0) dp[i][j] += dp[i-1][j];
			} else {
				dp[i][j] += dp[i-1][j+1];
				if(k >= 0) dp[i][j] += dp[i-1][j];
			}
			
			dp[i][j] %= 2012;
		}
	}
	
	cout << dp[N-1][0] << endl;
}
