// Time Travel

#include <iostream>

using namespace std;
const int MAXN = 80005;
int par[MAXN], val[MAXN], N;

int main() {
	cin >> N;
	val[0] = -1;
	
	for(int i = 1; i <= N; i++) {
		char c;
		cin >> c;
		if(c == 'a') {
			par[i] = i-1;
			cin >> val[i];
		} else if (c == 's') {
			par[i] = par[par[i-1]];
			val[i] = val[par[i-1]];
		} else if (c == 't') {
			int t;
			cin >> t;
			par[i] = par[t-1];
			val[i] = val[t-1];
		}
		
		cout << val[i] << endl;
	}
}
