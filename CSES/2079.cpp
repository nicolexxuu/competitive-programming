#include <bits/stdc++.h>

using namespace std;

const int MXN = 200005;

int n;
vector<int> adj[MXN];
int subtree_size[MXN];

void get_subtree_size(int node, int par = -1) {
	int sz = 1;
	for(int to : adj[node]) {
		if(to == par) continue;
		get_subtree_size(to, node);
		sz += subtree_size[to];
	}
	subtree_size[node] = sz;
}

int get_centroid(int node, int par = -1) {
	for(int to : adj[node]) {
		if(to != par && subtree_size[to] > n/2) 
			return get_centroid(to, node);
	}
	return node;
}

int main() {
	cin >> n;
	for(int i = 0; i < n-1; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	
	get_subtree_size(0);
	cout << get_centroid(0) + 1 << endl;
}
