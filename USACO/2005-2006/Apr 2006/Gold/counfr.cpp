#include <bits/stdc++.h>

using namespace std;

int N, T[400][400], dp[400];
pair<int, int> P[400];

int main () {
	cin >> N;
	for(int i = 0; i < N; i++) {
		cin >> P[i].first;
		P[i].second = i;
	}
	
	sort(P, P + N);
	for(int i = 0; i < N; i++)
		for(int j = 0; j < N; j++)
			cin >> T[i][j];
	
	int mx = 0;
	for(int i = 0; i < N; i++) 
		if ( T[0][P[i].second] <= P[i].first) dp[i] = 1;
	
	for(int i = 0; i < N; i++) {
		for(int j = 0; j < i; j++) 
			if(P[j].first + T[P[j].second][P[i].second] <= P[i].first && dp[j] > 0) dp[i] = max(dp[i], dp[j] + 1);
		mx = max(mx, dp[i]);
	}
			
	cout << max(mx, dp[0]) << endl;
}
