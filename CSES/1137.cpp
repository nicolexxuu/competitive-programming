#include <bits/stdc++.h>
#define ll long long

using namespace std;

const int MX = 2e5 + 5;
vector<int> adj[MX];
int timer = 0, st[MX], en[MX];
int v[MX];

void dfs(int node, int parent) {
	st[node] = timer++;
	for(int to : adj[node]) {
		if(to != parent) dfs(to, node);
	}
	en[node] = timer-1;
}

struct SegTree {
	int sz;
	ll *tree;
	
	SegTree(int N) {
		sz = N;
		tree = new ll[sz*2]; // why
		memset(tree, 0, 2 * sz * sizeof(ll));
	}
	
	void upd(int idx, ll x) {
		idx += sz;
		tree[idx] = x;
		for(int i = idx/2; i > 0; i /= 2) tree[i] = tree[i*2] + tree[i*2+1];
	}
	
	ll query(int lo, int hi) {
		lo += sz, hi += sz;
		ll total = 0;
		
		while(lo <= hi) {
			if(lo % 2 == 1) total += tree[lo++];
			if(hi % 2 == 0) total += tree[hi--];
			
			lo /= 2, hi /= 2;
		}
		
		return total;
	}
};

int main() {
	int n, q;
	cin >> n >> q;
	for(int i = 1; i <= n; i++) cin >> v[i];
	for(int i = 0; i < n-1; i++) {
		int a, b;
		cin >> a >> b;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	
	dfs(1, 0);
	SegTree tree(n);
	for(int i = 1; i <= n; i++) tree.upd(st[i], v[i]);
	
	for(int i = 0; i < q; i++) {
		int t; cin >> t;
		if(t == 1) {
			int s, x;
			cin >> s >> x;
			tree.upd(st[s], x);
		} else {
			int s; 
			cin >> s;
			cout << tree.query(st[s], en[s]) << endl;
		}
	}
}
