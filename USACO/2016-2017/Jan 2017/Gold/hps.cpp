// Hoof, Paper, Scissors

#include <iostream>
#include <algorithm>
#include <fstream>

using namespace std;
int dp[2][3][21];

int main() {
	ifstream cin("hps.in");
	ofstream cout("hps.out");
	
	int N, K;
	cin >> N >> K;
	int goal[N];
	
	for(int i = 0; i < N; i++) {
		char c;
		cin >> c;
		if(c == 'P') goal[i] = 1;
		else if(c == 'H') goal[i] = 2;
		else goal[i] = 0;
	}

	dp[0][goal[0]][0] = 1;
	
	for(int i = 1; i < N; i++) { // turn
		for(int p = 0; p < 3; p++) { // currently playing
			int won = (p == goal[i]) ? 1 : 0;
			dp[1][p][0] = dp[0][p][0] + won;
			
			
			for(int c = 1; c <= K; c++) { // # of changes
				for(int b = 0; b < 3; b++) { // changing from
					if(p == b) dp[1][p][c] = max(dp[1][p][c], dp[0][p][c] + won);
					else dp[1][p][c] = max(dp[1][p][c], dp[0][b][c-1] + won);
				}	
			}
		}
		
		swap(dp[0], dp[1]);
	}
	
	int mx = 0;
	for(int i = 0; i < 3; i++) {
		for(int j = 0; j <= K; j++) {
			mx = max(mx, dp[0][i][j]);
		}
	}
	
	cout << mx << endl;
}
