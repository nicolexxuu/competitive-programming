//Angry Cows

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

#define INF 2000000000

int main () {
	int N;
	cin >> N;
	vector<int> pos(N);
	for(int i = 0; i < N; i++) {
		cin >> pos[i];
		pos[i] *= 2;
	}
	
	sort(pos.begin(), pos.end());
	vector<int> DP[2];
	for(int i = 0; i < 2; i++) {
		int ind = 0;
		DP[i].resize(N, INF);
		DP[i][0] = 0;
		for(int j = 1; j < N; j++) {
			while(ind < j-1 && abs(pos[j] - pos[ind+1]) > DP[i][ind+1] + 2) ind++;
			DP[i][j] = min(abs(pos[j] - pos[ind]), DP[i][ind+1] + 2);
		}
		reverse(pos.begin(), pos.end());
	}
	
	reverse(DP[1].begin(), DP[1].end());
	int i = 0, j = N-1;
	int res = INF;
	while(i < j) {
		res = min(res, max((pos[j] - pos[i])/2, max(DP[0][i], DP[1][j]) + 2));
		if(DP[0][i+1] < DP[1][j-1]) i++;
		else j--;
	}
	
	cout << res/2 << "." << (res % 2 ? 5 : 0) << endl;
}
