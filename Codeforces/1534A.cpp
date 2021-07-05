#include <bits/stdc++.h>

using namespace std;

int main() {
	int t; cin >> t;
	for(int z = 0; z < t; z++) {
		int n, m;
		cin >> n >> m;
		char grid[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) { 
				cin >> grid[i][j];
			}
		}
		
		bool ok1 = true, ok2 = true;
		char rf[n][m], wf[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if((i+j)%2 == 0) {
					rf[i][j] = 'R';
					wf[i][j] = 'W';
				} else {
					rf[i][j] = 'W';
					wf[i][j] = 'R';
				}
				
				if(grid[i][j] != '.') {
					if(grid[i][j] != rf[i][j]) ok1 = false;
					if(grid[i][j] != wf[i][j]) ok2 = false;
				}
			}
		}

		if(ok1) {
			cout << "YES" << endl;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					cout << rf[i][j];
				}
				cout << endl;
			}
		} else if(ok2) {
			cout << "YES" << endl;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					cout << wf[i][j];
				}
				cout << endl;
			}
		} else {
			cout << "NO" << endl;
		}
	}
}
