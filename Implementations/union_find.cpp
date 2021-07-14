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
	a = root(a);
	b = root(b); 
	if(a == b) return;
	
	if(sz[a] < sz[b]) swap(a, b);
	
	arr[b] = a;
	sz[a] += sz[b];
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
