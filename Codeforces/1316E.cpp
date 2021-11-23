#include <bits/stdc++.h>
#define ll long long

using namespace std;

struct Person {
	int a;
	int s[7];
	
	bool operator<(const Person& other) { return a > other.a; }
};

vector<Person> people(100001);
ll dp[100001][1<<7];

int main() {
	int n, p, k;
	cin >> n >> p >> k;
	
	for(int i = 0; i < n; i++) cin >> people[i].a;
	
	for(int i = 0; i < n; i++)
		for(int j = 0; j < p; j++)
			cin >> people[i].s[j];
	
	sort(people.begin(), people.end());
	
	for(int i = 1; i <= n; i++) {
		for(int curr = 0; curr < (1<<p); curr++) {
			if(__builtin_popcount(curr) > i) continue;
			for(int pos = 0; pos < p; pos++) {
				if((curr & (1<<pos)) == 0) continue;
				dp[i][curr] = max(dp[i][curr], dp[i-1][curr - (1<<pos)] + people[i-1].s[pos]);
			}
			
			if((i == 1 && curr == 0) || (i <= k + __builtin_popcount(curr) && dp[i-1][curr] != 0)) 
				dp[i][curr] = max(dp[i][curr], dp[i-1][curr] + people[i-1].a);
			else 
				dp[i][curr] = max(dp[i][curr], dp[i-1][curr]);
		}
	}
	
	cout << dp[n][(1<<p)-1] << endl;
}
