// Union-Find Implementation

#include <bits/stdc++.h>
#define MAXN 200001
 
using namespace std;

int N, M;
int arr[MAXN], sz[MAXN], order[MAXN], open[MAXN], connected[MAXN];
vector<int> adj[MAXN];

void init() {
	for(int i = 0; i < N; i++) {
		arr[i] = i;
		sz[i] = 1;
	}
}

int root(int a) {
	while(arr[a] != a) {
		arr[a] = arr[arr[a]];
		a = arr[a];
	}
	return a;
}

void merge(int a, int b) {
	int r_a = root(a);
	int r_b = root(b); 
	if(r_a == r_b) return;
	
	if(sz[r_a] < sz[r_b]) {
		arr[r_a] = r_b;
		sz[r_b] += sz[r_a];
	} else {
		arr[r_b] = r_a;
		sz[r_a] += sz[r_b];
	}
}

int main() {
	ifstream cin("closing.in");
	ofstream cout("closing.out");
	cin >> N >> M;	
	init();

	for(int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	
	for(int i = 1; i <= N; i++) {
		int a;
		cin >> a;
		order[N-i] = a;
	}	
	
	for(int i = 0; i < N; i++) {
		int add = order[i];
		for(int to: adj[add]) if(open[to]) merge(to, add);
		
		if(sz[root(add)] == i + 1) connected[i] = 1;
		open[add] = true;
	}
	
	for(int i = N-1; i >= 0; i--) {
		if(connected[i]) cout << "YES" << endl;
		else cout << "NO" << endl;
	}	
}	
