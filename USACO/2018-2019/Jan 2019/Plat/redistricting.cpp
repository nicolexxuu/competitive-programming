#include <bits/stdc++.h>
#include <iostream>

using namespace std;

int pref[300005], dp[300005];

int main() {
	int N, K;
	
	cin >> N >> K;
	for(int i = 1; i <= N; i++) {
		char c;
		cin >> c;
		if(c == 'H') pref[i] = pref[i-1] - 1; // <= add 1 (G - majority)
		else pref[i] = pref[i-1] + 1; // > subtract one (H majority)
	}
	
	multiset<int> curr;
	multiset<int, greater<int>> prefAt[300005];
	
	curr.insert(0);
	prefAt[0].insert(0);
	
    	for(int i = 1; i <= N; i++) {
		if(i > K) {
			int f = i-K-1;
			curr.erase(curr.find(dp[f]));
			prefAt[dp[f]].erase(prefAt[dp[f]].find(pref[f]));
		}
		
		int bVal = *curr.begin();
		int bPref = *prefAt[bVal].begin();
		
		if(bPref > pref[i]) dp[i] = bVal;
		else dp[i] = bVal + 1;
		
		curr.insert(dp[i]);
		prefAt[dp[i]].insert(pref[i]);
	}
	
	cout << dp[N] << endl;
}
