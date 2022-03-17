// Cat and Mice - 2017 ICPC North American Qualifier Contest
// binary search w/ doubles + bitmask DP

#include <bits/stdc++.h>

using namespace std;

const int MAXN = 16;
const double PRECISION = 1e-3;
const int INF = 1e9;

int n;
int mice[MAXN][3];
double dp[1 << MAXN][MAXN], p[MAXN];
double m; 

double dist(int i1, int i2) {
	return sqrt((mice[i1][0] - mice[i2][0]) * (mice[i1][0] - mice[i2][0]) + (mice[i1][1] - mice[i2][1]) * (mice[i1][1] - mice[i2][1]));
}

bool possible(double init) {
	for(int mask = 1; mask < (1 << n); mask++) 
		for(int i = 0; i < n; i++) 
			dp[mask][i] = INF;
	
	for(int i = 0; i < n; i++) {
		double finish = sqrt((mice[i][0]) * (mice[i][0]) + (mice[i][1]) * (mice[i][1])) / init;
		if(finish <= mice[i][2]) dp[(1 << i)][i] = finish;
	}
	
	for(int mask = 1; mask < (1 << n); mask++) {
		for(int last = 0; last < n; last++) {
			if(!(mask & (1 << last))) continue;
			int before = mask - (1 << last);
			for(int prev = 0; prev < n; prev++) {
				if(!(before & (1 << prev)) || dp[before][prev] == INF) continue;
				
				int before = mask - (1 << last);
				double old_v = init * p[__builtin_popcount(before)];
				double finish = dist(prev, last) / old_v + dp[before][prev];
				
				if(finish <= mice[last][2]) dp[mask][last] = min(dp[mask][last], finish);
			}
		}
	}
	
	for(int i = 0; i < n; i++)
		if(dp[(1 << n) - 1][i] != INF) return true;
	return false;
}

int main() {
	cin >> n;
	for(int i = 0; i < n; i++) cin >> mice[i][0] >> mice[i][1] >> mice[i][2];
	cin >> m;
	
	p[1] = m;
	for(int i = 2; i <= n; i++) p[i] = p[i-1] * m;
	
	double lo = 0, hi = 3e5;
	while (hi - lo > PRECISION) {
		double mid = (lo + hi) / 2;
		if (possible(mid)) hi = mid;
		else lo = mid;
	}

	cout << lo << '\n';
}
