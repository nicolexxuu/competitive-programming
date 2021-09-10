#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

#define f first
#define s second

const int MXN = 1e5;
int sz[MXN], root[MXN];

int rt(int a) {
	while(a != root[a]) {
		root[a] = root[root[a]];
		a = root[a];
	}
	
	return a;
}

void unite(int a, int b) {
	a = rt(a), b = rt(b);
	if(a == b) return;
	if(sz[a] < sz[b]) swap(a, b);
	
	root[b] = a;
	sz[a] += sz[b];
}

int main() {
	int n, m;
	cin >> n >> m;
	
	for(int i = 0; i < n; i++) {
		root[i] = i;
		sz[i] = 1;
	}
	
	vector<pair<ll, pair<int, int>>> edges;
	for(int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		a--; b--;
		edges.push_back({c, {a, b}});
	}
	sort(begin(edges), end(edges));
	
	ll total = 0, cnt = 0;
	for(auto& e : edges) {
		ll c = e.f;
		int a = e.s.f, b = e.s.s;
		if(rt(a) != rt(b)) {
			cnt++;
			unite(a, b);
			total += c;
		}
	}
	
	if(cnt < n-1) cout << "IMPOSSIBLE" << '\n';
	else cout << total << '\n';
}
