#include <bits/stdc++.h>
#define ll long long

using namespace std;

const int MAXN = 200005;
vector<int> adj[MAXN];
int timer = 1, st[MAXN], en[MAXN];
int v[MAXN];
ll BIT[MAXN];

void dfs(int node, int par) {
	st[node] = timer++;
	for(int to : adj[node]) {
		if(to != par) dfs(to, node);
	}
	en[node] = timer - 1;
}

void add(int idx, int val) {
	while(idx < MAXN) {
		BIT[idx] += val;
		idx += idx & -idx;
	}
}

ll get(int idx) {
	ll sum = 0;
	
	while(idx > 0) {
		sum += BIT[idx];
		idx -= idx & -idx;
	}
	
	return sum;
}

int main() {
	int n, q;
	cin >> n >> q;
	
	for(int i = 0; i < n; i++) cin >> v[i];
	for(int i = 0; i < n-1; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	
	dfs(0,-1);
	
	for(int i = 0; i < n; i++) {
		add(st[i], v[i]);
		add(en[i] + 1, -v[i]);
	}
	
	for(int i = 0; i < q; i++) {
		int t; cin >> t;
		int s; cin >> s;
		s--;
		if(t == 1) {
			int x; cin >> x;
			int toAdd = x - v[s];
			add(st[s], toAdd);
			add(en[s] + 1, -toAdd);
			v[s] = x;
		} else {
			cout << get(st[s]) << endl;
		}
	}
}
