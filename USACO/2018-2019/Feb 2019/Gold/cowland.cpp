#include <bits/stdc++.h>
#define ll long long

using namespace std;

const int MAXN = 100005;
vector<int> adj[MAXN];
int timer1 = 1, timer2 = 1, st[MAXN], en[MAXN], tin[MAXN*2], euler[MAXN*2];
int segtree[MAXN*4];
int e[MAXN];
int BIT[MAXN];

void dfs(int node, int par) {
	st[node] = timer1++;
	for(int to : adj[node]) {
		if(to != par) dfs(to, node);
	}
	en[node] = timer1 - 1;
}

void dfs2(int node, int par) {
	tin[node] = timer2;
	euler[timer2++] = node;
	for(int to : adj[node]) {
		if(to != par) {
			dfs2(to, node);
			euler[timer2++] = node;
		}
	}
}

void add(int idx, int val) {
	while(idx < MAXN) {
		BIT[idx] ^= val;
		idx += idx & -idx;
	}
}

int get(int idx) {
	int sum = 0;
	
	while(idx > 0) {
		sum ^= BIT[idx];
		idx -= idx & -idx;
	}
	
	return sum;
}

int mn_tin(int a, int b) { // depth works too
	if(a == -1) return b;
	if(b == -1) return a;
	return (tin[a] < tin[b]) ? a : b; 
}

// O(N)
void build(int node, int l, int r) {
	if(l == r) segtree[node] = euler[l];
	else {
		int m = (l + r)/2;
		build(node * 2, l, m);
		build(node * 2 + 1, m + 1, r);
		segtree[node] = mn_tin(segtree[node * 2], segtree[node * 2 + 1]);
	}
}

// O(log N)
int query(int a, int b, int node, int l, int r) {
	if(l > b || r < a) return -1;
	if(l >= a && r <= b) return segtree[node];
	int m = (l + r)/2;
	return mn_tin(query(a, b, node * 2, l, m), query(a, b, node * 2 + 1, m + 1, r));
}

int lca(int a, int b) {
	if(tin[a] > tin[b]) swap(a, b);
	return query(tin[a], tin[b], 1, 0, timer2-1);
}
int main() {
	ifstream cin("cowland.in");
	ofstream cout("cowland.out");
	int N, Q;
	cin >> N >> Q;
	
	for(int i = 1; i <= N; i++) cin >> e[i];
	for(int i = 0; i < N-1; i++) {
		int a, b;
		cin >> a >> b;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	
	dfs(1, 0);
	dfs2(1, 0);
	build(1, 0, timer2-1);
	
	for(int i = 1; i <= N; i++) {
		add(st[i], e[i]);
		add(en[i] + 1, e[i]);
	}
	
	for(int k = 0; k < Q; k++) {
		int t, i; 
		cin >> t >> i;
		
		if(t == 1) {
			int v; cin >> v;
			int toAdd = v ^ e[i];
			add(st[i], toAdd);
			add(en[i] + 1, toAdd);
			e[i] = v;
		} else {
			int j; cin >> j;
			cout << (get(st[i]) ^ get(st[j]) ^ e[lca(i, j)]) << endl;
		}
	}
}
