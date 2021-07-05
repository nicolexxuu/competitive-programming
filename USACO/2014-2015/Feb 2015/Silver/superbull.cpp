#include <iostream>
#include <algorithm>
#include <limits>
#define MAXN 2000
#define ll long long
#define INF 1000000000000000

using namespace std;

int id[MAXN];
ll dist[MAXN];
int N;

int main () {
	cin >> N;
	for(int i = 0; i < N; i++) {
		cin >> id[i];
		dist[i] = -1;
	}
	
	long res = 0;
	dist[0] = 0;
	
	for(int i = 0; i < N; i++) {
		int maxVal = -1;
		int maxInd = 0;

		for(int j = 0; j < N; j++) {
			if(dist[j] != INF && dist[j] > maxVal) {
				maxVal = dist[j];
				maxInd = j;
			}
		}
			
		dist[maxInd] = INF;
		res += maxVal;
		
		for(int j = 0; j < N; j++) 
			if(j != INF) dist[j] = max(dist[j], (ll)(id[maxInd] ^ id[j]));
	}
	
	cout << res << endl;
}
