#include <bits/stdc++.h>
#define ll long long
using namespace std;

const ll MOD = 1000000007;

ll dp[500][500];
ll choose[251][251];

ll add(ll a, ll b) { return (a + b) % MOD; }
ll mult(ll a, ll b) { return (a * b) % MOD; }

int main() {
	string s; cin >> s;
	int n = s.size();
	
	for(int i = 0; i < n; i++) dp[i][i-1] = 1;
	choose[0][0] = 1;
	for(int i = 1; i <= n/2; i++) {
		choose[i][0] = 1;
		for(int j = 1; j <= n/2; j++) choose[i][j] = add(choose[i-1][j], choose[i-1][j-1]);
	}
	
	for(int i = 0; i <= n; i++) dp[i+1][i] = 1;
	for(int len = 1; len <= n; len++) {
		for(int l = 0; l <= n - len; l++) {
			int r = l + len - 1;
			for(int i = l+1; i <= r; i++) {
				if(s[l] == s[i])
					dp[l][r] = add(dp[l][r], mult(mult(dp[l+1][i-1], dp[i+1][r]), choose[(r-l+1)/2][(i-l+1)/2]));
			}
		}
	}
	
	cout << dp[0][n-1] << '\n';
}
