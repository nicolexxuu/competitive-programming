#include <bits/stdc++.h>

#define MX 200005

using namespace std;

vector<int> adj[MX];
int timer = 1, tin[MX], euler[MX*2];
int layer = 1, depth[MX];
int st[MX*8]; // not memory-efficient implementation


void dfs(int node, int parent) {
	tin[node] = timer;
	euler[timer++] = node;
	depth[node] = layer++;
	for(int to : adj[node]) {
		if(to != parent) {
			dfs(to, node);
			euler[timer++] = node;
		}
	}
	layer--;
}

int mn_depth(int a, int b) { // tin works too
	if(a == -1) return b;
	if(b == -1) return a;
	return (depth[a] < depth[b]) ? a : b; 
}

void build(int node, int l, int r) {
	if(l == r) st[node] = euler[l];
	else {
		int m = (l + r)/2;
		build(node * 2, l, m);
		build(node * 2 + 1, m + 1, r);
		st[node] = mn_tin(st[node * 2], st[node * 2 + 1]);
	}
}

int query(int a, int b, int node, int l, int r) {
	if(l > b || r < a) return -1;
	if(l >= a && r <= b) return st[node];
	int m = (l + r)/2;
	return mn_tin(query(a, b, node * 2, l, m), query(a, b, node * 2 + 1, m + 1, r));
}

int main() {
	int n, q;
	cin >> n >> q;
	
	for(int i = 0; i < n-1; i++) {
		int a, b;
		cin >> a >> b;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
		
	dfs(1, 0);
	build(1, 1, timer);
	
	for(int i = 0; i < q; i++) {
		int a, b;
		cin >> a >> b;
		if(tin[a] > tin[b])
			swap(a, b);
		int lca = query(tin[a], tin[b], 1, 1, timer);
		cout << depth[a] + depth[b] - 2*depth[lca] << endl;
	}

}
