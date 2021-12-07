#include <bits/stdc++.h>

using namespace std;
const int MAXN = 7505;

int N, K;
bool mark[MAXN];
int main() {
	ifstream cin("walk.in");
	ofstream cout("walk.out");
	cin >> N >> K;
	
	vector<int> dist(N);
	for(int i = 0; i < N; i++) dist[i] = INT_MAX;
	dist[0] = 0;
	
	for(int in = 0; in < N; in++) {
		int minDist = INT_MAX;
		int minI = -1;
		
		for(int i = 0; i < N; i++) {
			if(!mark[i] && dist[i] < minDist) {
				minDist = dist[i];
				minI = i;
			}
		}
		
		mark[minI] = true;
		for(int i = 0; i < N; i++)
			if(!mark[i]) dist[i] = min(dist[i], int((2019201913ll*min(minI+1, i+1) + 2019201949ll*max(minI+1, i+1)) % 2019201997));
	}
	
	sort(dist.begin(), dist.end());
	cout << dist[N-K+1] << endl;
}
