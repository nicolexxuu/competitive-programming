#include <bits/stdc++.h> 

using namespace std;
int diff[1002][1002], ps[1002][1002];

int main() {
	ifstream cin("paintbarn.in");
	ofstream cout("paintbarn.out");

	int N, K;
	cin >> N >> K;
	
	for(int i = 0; i < N; i++) {
		int x1, x2, y1, y2;
		cin >> x1 >> y1 >> x2 >> y2;
		diff[x1+1][y1+1]++;
		diff[x2+1][y1+1]--;
		diff[x1+1][y2+1]--;
		diff[x2+1][y2+1]++;
	}
	
	int res = 0;
	for(int i = 1; i < 1000; i++) {
		for(int j = 1; j < 1000; j++) {
			ps[i][j] = ps[i-1][j] + ps[i][j-1] - ps[i-1][j-1] + diff[i][j];
			if(ps[i][j] == K) res++;
		}
	}
	
	cout << res << endl;
}
