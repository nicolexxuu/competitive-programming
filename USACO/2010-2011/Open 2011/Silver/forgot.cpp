#include <iostream>

using namespace std;

string dp[1000];
int main () {
	int L, NW;
	cin >> L >> NW;
	string dict[NW];
	string P;
	cin >> P;
	P = " " + P;
	for(int i = 0; i < NW; i++) cin >> dict[i];
	
	for(int i = 1; i <= L; i++) {
		//cout << "i: " << i << endl;
		for(string s: dict) {
			//cout << "s: " << s << endl;
			int ind = i;
			if(s.length()> ind) continue;
			for(int j = s.length()-1; j >= 0; j--) {
				char c = s[j];
				if(P[ind] != '?' && P[ind] != c) break;
				ind--;
			}
			
			int prev =  i-s.length();
			if (ind == prev && (prev == 0 || dp[prev] != "") && (dp[i] == "" || dp[i] > dp[prev] + s)) 
			 	dp[i] = dp[i-s.length()] + s;
		}
	}
	//for(int i = 0; i < L ; i++) cout << dp[i] << endl;
	cout << dp[L] << endl;
}
