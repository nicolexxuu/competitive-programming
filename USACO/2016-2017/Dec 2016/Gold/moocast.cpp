#include <algorithm>
#include <iostream>
#include <vector>
#define MAXN 1000
#define f first
#define s second

using namespace std;
int sz[MAXN], pa[MAXN];

int dist(int x1, int y1, int x2, int y2) {
	return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
}

int find(int a) {
	int b = a;
	while(pa[b] != b) {
		b = pa[b];
	}
	
	while(a != b) {
		int temp = pa[a];
		pa[a] = b;
		a = temp;
	}
	
	return b;
}

void uf(int a, int b) {
	a = find(a), b = find(b);
	if(a == b) return;
	
	if(sz[a] < sz[b]) {
		pa[a] = b;
		sz[b] += sz[a];
	} else {
		pa[b] = a;
		sz[a] += sz[b];
	}
}

int main() {
	int N;
	cin >> N;
	int coords[N][2];
	
	vector<pair<int, pair<int, int>>> edges;
	
	for(int i = 0; i < N; i++) {
		cin >> coords[i][0] >> coords[i][1];
		sz[i] = 1;
		pa[i] = i;
	}

	for(int i = 0; i < N; i++) {
		for(int j = 0; j < N; j++) {
			if(i == j) continue;
			edges.push_back( {dist(coords[i][0], coords[i][1], coords[j][0], coords[j][1]), {i, j}});
		}
	}
	
	sort(edges.begin(), edges.end());
	
	for(auto e: edges) {
		uf(e.s.f, e.s.s);
		
		if(sz[find(e.s.f)] == N) {
			cout << e.f << endl;
			return 0;
		}
	}
}
