#include <bits/stdc++.h>

using namespace std;

const int MXN = 200005;
vector<int> adj[MXN];
int n;

int dist[2][MXN];
int dfs(int u, int p, int d, int i) {
	dist[i][u] = d;
	
	int best = u;
	for(int to : adj[u]) {
		if(to == p) continue;
		int x = dfs(to, u, d+1, i);
		if(dist[i][x] > dist[i][best]) best = x;
	}
	
	return best;
}

void solve1() {
	int last1 = dfs(0, -1, 0, 0);
	int last2 = dfs(last1, -1, 0, 0);
	dfs(last2, -1, 0, 1);
	
	for(int i = 0; i < n; i++)
		cout << max(dist[0][i], dist[1][i]) << endl;
}



int max1[MXN], max2[MXN], c[MXN];
// max1[i] and max2[i] store length first- and second-longest paths from i to a leaf
// c[i] stores child of i on this longest path ^
void _dfs1(int u, int p) {
	for(int x : adj[u]) {
		if(x == p) continue;
		_dfs1(x, u);
		
		if(max1[x] + 1 > max1[u]) {
			max2[u] = max1[u];
			max1[u] = max1[x] + 1;
			c[u] = x;
		} else if(max1[x] + 1 > max2[u]) {
			max2[u] = max1[x] + 1;
		}
	}
}

void _dfs2(int u, int p) {
	for(int x : adj[u]) {
		if(x == p) continue;
		if(c[u] == x) { // longest path from parent passes thru x
			if(max1[x] < max2[u] + 1) {
				max2[x] = max1[x];
				max1[x] = max2[u] + 1;
				c[x] = u;
			} else {
				max2[x] = max(max2[x], max2[u] + 1);
			}
		} else {
			max2[x] = max1[x];
			max1[x] = max1[u] + 1;
			c[x] = u;
		}
		
		_dfs2(x, u);
	}
}

void solve2() {
	_dfs1(0, -1);
	_dfs2(0, -1);
	
	for(int i = 0; i < n; i++) cout << max1[i] << endl;
}

int main() {
	cin.tie(0)->sync_with_stdio(0);
	cin >> n;
	
	for(int i = 0; i < n-1; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	
//	solve1();
	solve2();

}
