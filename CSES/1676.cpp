// Union-Find Implementation

#include <iostream>
#include <algorithm>
#include <vector>
#define MAXN 100000
 
using namespace std;

int N, M;
int comp = 0, mx = 0;
int arr[MAXN], sz[MAXN];

void init() {
	mx = 1;
	comp = N;
	
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
		mx = max(mx, sz[r_b]);
	} else {
		arr[r_b] = r_a;
		sz[r_a] += sz[r_b];
		mx = max(mx, sz[r_a]);
	}
}

int main() {
	cin >> N >> M;
	init();

	for(int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		
		if(root(a) != root(b)) {
			merge(a, b);
			comp--;
		}
		
		cout << comp << " " << mx << endl;
	}
}
