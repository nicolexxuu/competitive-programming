#include <bits/stdc++.h>

using namespace std;

bool dp[100001];

int main() {
	int n; cin >> n;
	vector<int> x(n);
	for(int&v : x) cin >> v;
	for(int i = 1; i <= n; i++) cin >> x[i];
	
	dp[0] = true;
	for(int c : x) {
		for(int i = 100000; i >= c; i--) {
			dp[i] |= dp[i-c];
		}
	}
	
	vector<int> possible;
	for(int i = 1; i <= 100000; i++) 
		if(dp[i]) possible.push_back(i);
	
	cout << possible.size() << '\n';
	for(int i : possible) cout << i << ' ';
}
