#include <fstream>
#include <iostream>
#include <algorithm>
using namespace std;

int N, K;
int skill[10000];
int dp[10000];

int main() {
	ifstream fin("teamwork.in");
	fin >> N >> K;
	
	for(int i = 0; i < N; i++) fin >> skill[i];
	
	
	for(int i = 0; i < N; i++) {
		int mx = skill[i];
		
		for(int j = i; j >= 0 && i - j < K; j--) {
			mx = max(mx, skill[j]);
			if(j == 0) dp[i] = max(dp[i], mx * (i+1));
			else dp[i] = max(dp[i], dp[j-1] + mx * (i-j+1));
		}
	}
	
	ofstream fout("teamwork.out");
	fout << dp[N-1] << '\n';
	cout << dp[N-1] << '\n';
}
