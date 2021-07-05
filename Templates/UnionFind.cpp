// Union-Find Implementation

#include <iostream>
#include <algorithm>
#include <vector>
#define MAXN 10000
 
using namespace std;

int N, M;
int arr[MAXN], sz[MAXN];

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
	cin >> N >> M;
	init();

	for(int i = 0; i < M; i++) {
		int a, b;
		a--; b--;
		cin >> a >> b;
		merge(a, b);
	}
}
