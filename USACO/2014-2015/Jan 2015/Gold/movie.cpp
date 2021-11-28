#include <bits/stdc++.h>

using namespace std;

int dp[1<<20];
int D[20];
vector<int> times[20];

int binarySearch(int m, int end) { 
	int lo = 0, hi = times[m].size()-1;
	if(times[m][lo] > end) return -1;
	while(lo < hi) {
		int mid = (lo + hi + 1)/2;
		if(times[m][mid] <= end) lo = mid;
		else hi = mid-1;
	}
	
	if(times[m][hi]+D[m] > end) return times[m][hi]+D[m];
	return -1;
}

int main() {
	ifstream cin("movie.in");
	ofstream cout("movie.out");
	int N, L;
	cin >> N >> L;
	for(int i = 0; i < N; i++) {
		cin >> D[i];
		int C; cin >> C;
		
		for(int j = 0; j < C; j++) {
			int a; cin >> a;
			times[i].push_back(a);
		}
	}
	
	int movies = N+1;
	for(int mask = 0; mask < (1<<N); mask++) {
		for(int last = 0; last < N; last++) {
			if(!(mask & (1<<last))) continue;
			int before = mask - (1<<last);
			dp[mask] = max(dp[mask], binarySearch(last, dp[before]));
		}
		
		if(dp[mask] >= L) movies = min(movies, __builtin_popcount(mask));
	}
	
	
	if(movies == N+1) cout << -1 << endl;
	else cout << movies << endl;
}
