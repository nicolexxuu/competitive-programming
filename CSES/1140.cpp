#include <bits/stdc++.h>
#define pii pair<int, int>
#define ll long long

using namespace std;

vector<pii> endings[400001];
long long dp[400001];
int projects[200000][3];

int main() {
	int n; cin >> n;
	map<int, int> compress;
	
	for(int i = 0; i < n; i++) {
		cin >> projects[i][0] >> projects[i][1] >> projects[i][2];
		compress[projects[i][0]], compress[projects[i][1]];
	}

	int idx = 1;
	for(auto&c : compress) c.second = idx++;
	for(auto&p : projects) endings[compress[p[1]]].push_back({compress[p[0]], p[2]});

	for(int day = 1; day <= idx; day++) {
		dp[day] = dp[day-1];
		for(pii p : endings[day]) dp[day] = max(dp[day], dp[p.first-1] + p.second);
	}
	
	cout << dp[idx] << endl;
}
