#include <bits/stdc++.h>
#define ll long long
using namespace std;

const int MAXN = 5001;
ll dp[MAXN][MAXN];
int A[MAXN];

int main() {
	cin.tie(0)->sync_with_stdio(0);
	ifstream cin("threesum.in");
	ofstream cout("threesum.out");
	
	int N, Q;
	cin >> N >> Q;
	for(int i = 0; i < N; i++) cin >> A[i];
	
	vector<int> freq(2000001);
	for(int l = 0; l < N; l++) {
		for(int r = l+1; r < N; r++) {
			int b = -(A[l] + A[r]) + 1000000;
			if(b >= 0 && b <= 2000000) dp[l][r] = freq[b];
			freq[A[r]+1000000]++;
		}
		
		for(int i = l+1; i < N; i++) freq[A[i]+1000000]=0;
	}
	
//	this method TLEs
//	for(int len = 3; len <= N; len++) {
//		for(int l = 0; l + len <= N; l++) {
//			int r = l + len - 1;
//			dp[l][r] += dp[l+1][r] + dp[l][r-1] - dp[l+1][r-1];
//		}
//	}

	for(int s = N-1; s >= 0; s--)
			for(int e = s+2; e < N; e++)
				dp[s][e] += dp[s+1][e] + dp[s][e-1] - dp[s+1][e-1];
				
	for(int i = 0; i < Q; i++) {
		int a, b;
		cin >> a >> b;
		cout << dp[a-1][b-1] << endl;
	}
}

