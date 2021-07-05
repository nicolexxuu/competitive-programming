#include <iostream>
#include <algorithm>
#define INF 1000000000

using namespace std;

int pos[10000];
int dp[100001];

int main () {
	int N, L;
	cin >> N >> L;
	for(int i = 0; i < N; i++) cin >> pos[i];
	int D = L/(N-1);
	
	int oldl = 0, oldr = 0;
	dp[0] = pos[0];
	for(int i = 1; i < N; i++) {
		int l = D*i, r = (D+1)*i;
		int p = pos[i];
		for(int j = r; j >= l; j--) {
			dp[j] = INF;
			if(j-D-1 >= oldl) dp[j] = min(dp[j], dp[j-D-1] + abs(j-p));
			if(j-D <= oldr) dp[j] = min(dp[j], dp[j-D] + abs(j-p));
		}

		oldl = l, oldr = r;
	}
	
	cout << dp[L] << '\n';
	
	
}
