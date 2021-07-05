#include <iostream>
#include <fstream>
#include <algorithm>
#include <cstring>

using namespace std;

int N, H;
int P[100], C[100], dp[55001];
						//i  // min
int INF = 10000000;

int main () {
	cin >> N >> H;
	memset(dp, 63, sizeof(dp));
	
	for(int i = 0; i < N; i++) cin >> P[i] >> C[i];
	
	dp[0] = 0; 
	for(int i = 0; i < N; i++) {
		for(int j = P[i]; j <= H + 5000; j++) {
			dp[j] = min(dp[j], dp[j-P[i]] + C[i]);

		}
	}
	
	int mn = INF;
	//cout << endl;
	for(int i = H; i <= 55000; i++) mn = min(mn, dp[i]);
	cout << mn << '\n';
}

