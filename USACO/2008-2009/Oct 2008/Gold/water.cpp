#include <iostream>
#define MAXN 305

using namespace std;

int N;
int W[MAXN], adj[MAXN][MAXN], dist[MAXN];
bool mark[MAXN];

int main (){
	cin >> N;
	for(int i = 0; i < N; i++) {
		cin >> W[i];
		dist[i] = W[i];
	}
	
	for(int i = 0; i < N; i++) {
		for(int j = 0; j < N; j++) {
			cin >> adj[i][j];
		}
	}
	
	int result = 0;
	for(int i = 0; i < N; i++) {
		int minInd = 0;
		int minVal = 2000000000;
		for(int j = 0; j < N; j++) {
			if(!mark[j] && dist[j] < minVal) {
				minInd = j;
				minVal = dist[j];
			}
		}
		
		result += minVal;
		mark[minInd] = true;
		
		for(int j = 0; j < N; j++) {
			if(mark[j]) continue;
			if(adj[minInd][j] < dist[j]) {
				dist[j] = adj[minInd][j];
			}
		}
	}
	
	cout << result << endl;
}
