#include <bits/stdc++.h>

using namespace std;

int dp[305][305], A[305];

int main() {
	int N; cin >> N; 
	for(int i = 1; i <= N; i++) cin >> A[i];
	
	for(int i = N; i >= 1; i--) {
		for(int j = i+1; j <= N; j++) {
			if(A[i] == A[j]) dp[i][j] = 1 + dp[i+1][j-1];
			for(int k = i+1; k < j; k++) dp[i][j] = max(dp[i][j], dp[i][k] + dp[k][j]);
		}
	}
	
	cout << N - dp[1][N] << endl;
}
