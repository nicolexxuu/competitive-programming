#include <iostream>
#include <string>
using namespace std;

int dp[200001], res;
string p[201], s;
int S, P;
int result;

int main () {
	while(true) {
		cin >> p[P];
		if(p[P] == ".") break;
		P++;
	}
	s = " ";
	while(cin) {
		string line;
		cin >> line;
		s += line;
	}
	
	S = s.length()-1;
	
	dp[0] = 1;
	for(int i = 1; i <= S; i++) {
		for(int j = 0; j <P; j++) {
			string pre = p[j];
			int start = i - pre.length() + 1;
			if(start >= 0 && pre.compare( s.substr(start, pre.length())) == 0) {
				if(dp[start-1] == 0) continue;
				dp[i] = 1;
				result = i;
				break;
			}
		}
	}
	
	cout << result << '\n';
}
