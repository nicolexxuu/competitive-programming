#include <bits/stdc++.h> 
#define ll long long
using namespace std; 

ll r[101], ps[101];
ll dp[8][101][101];

int main() {
	ifstream cin("cbarn2.in");
	ofstream cout("cbarn2.out");

	int n, k;
	cin >> n >> k;
	
	for(int i = 1; i <= n; i++) {
		cin >> r[i];
		ps[i] = ps[i-1] + r[i];
		
		for(int f = 1; f <= n; f++)
			for(int j = 1; j <= k; j++)
				dp[j][i][f] = LLONG_MAX;
	}
	
	ll res = LLONG_MAX;
	for(int i = 1; i <= n; i++) {
		ll sum = 0;
		for(int j = i+1; j <= n; j++) sum += r[j] * (j-i);
		for(int j = 1; j < i; j++) sum += r[j] * (n-i+j);
		
		dp[1][i][i] = sum;
		res = min(res, sum);
	}
	
	for(int d = 2; d <= k; d++) {
		for(int j = d; j <= n; j++) {
			for(int i = j-1; i >= d-1; i--) {
				for(int f = 1; f <= i - (d - 2); f++) {  
					dp[d][j][f] = min(dp[d][j][f], dp[d-1][i][f] - (ps[n] - ps[j-1] + ps[f-1]) * (j - i));
					res = min(res, dp[d][j][f]);
				}
			}
		}
	}
	
	cout << res << endl;
}
