#include <bits/stdc++.h>
#define pii pair<int, int> 

using namespace std;

int N, K;
int B[50001], S[51][51];
int dist[50001][51];

int main() {
	cin >> N >> K;
	for(int i = 1; i <= N; i++) cin >> B[i];
	for(int i = 1; i <= K; i++) {
		string s; cin >> s;
		for(int j = 1; j <= K; j++) {
			S[i][j] = s[j-1] == '1';
		}
		S[i][0] = S[i][B[N]];
	}
	B[N] = 0; // set N to breed 0 to differentiate from cow 1
	
	for(int c = 1; c <= N; c++) 
		for(int b = 0; b <= K; b++)
			dist[c][b] = -1;

	deque<pii> toVisit;
	toVisit.push_back({1, B[1]});
	dist[1][B[1]] = 0;
	
	while(toVisit.size() != 0) {
		int c = toVisit.front().first, b = toVisit.front().second;
		toVisit.pop_front();
		
		// message is just "passing through"
		if(c < N && dist[c+1][b] == -1) {
			dist[c+1][b] = dist[c][b] + 1;
			toVisit.push_back({c+1, b});
		}
		
		if(c > 1 && dist[c-1][b] == -1) {
			dist[c-1][b] = dist[c][b] + 1;
			toVisit.push_back({c-1, b});
		}
		
		// message lands in an eligible cow's hands
		if(S[b][B[c]] && dist[c][B[c]] == -1) {
			dist[c][B[c]] = dist[c][b];
			toVisit.push_front({c, B[c]});
		}
	}

	cout << dist[N][0] << endl;
}
