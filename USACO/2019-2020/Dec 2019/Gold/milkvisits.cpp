#include <bits/stdc++.h>
#define MAXN 100005
#define pb push_back 
#define f first
#define s second
#define pii pair<int, int>

using namespace std;
int N, M;
vector<int> adj[MAXN], req[MAXN];
int type[MAXN], q[MAXN][2], color[MAXN];
bool ans[MAXN];
pair<int, int> range[MAXN];

int co = 0;
void dfs(int x, int y) { // euler tour - track in/out time
	range[x].f = co++;
	for(int t: adj[x]) if(t != y) dfs(t, x);
	range[x].s = co - 1;
}

bool anc(int a, int b) {
	return range[a].f <= range[b].f && range[a].s >= range[b].s;
}

vector<int> stk[MAXN];
vector<int> at;

void dfs2(int a, int y) {
	stk[type[a]].push_back({a, at.size()});
	at.pb(a);
	for(int r: req[a]) {
		if(stk[color[r]].size() == 0) continue;
		pii y = stk[color[r]].back();
		if(y.f == a) ans[r] = true;
		else {
			int Y = at[y.s + 1];
			if(!anc(Y, q[r][0] + q[r][1] - a)) ans[r] = true;
		}
	}
	
	for(int t: adj[a]) if(t != y) dfs2(t, a);
	stk[type[a]].pop_back();
	at.pop_back();
};

int main() {
	ifstream cin("milkvisits.in");
	ofstream cout("milkvisits.out");
	cin >> N >> M;
	for(int i = 0; i < N; i++) cin >> type[i];
	
	for(int i = 0; i < N-1; i++) {
		int x, y;
		cin >> x >> y;
		x--; y--;
		adj[x].pb(y);
		adj[y].pb(x);
	}
	
	for(int i = 0; i < M; i++) {
		int a, b, c;
		cin >> a >> b >> color[i];
		a--; b--;
		req[a].pb(i);
		req[b].pb(i);
		q[i][0] = a, q[i][1] = b;
	}
	
	dfs(0, 0);
	dfs2(0, 0);
	
	for(int i = 0; i < M; i++) {
		if(ans[i]) cout << 1;
		else cout << 0;
	}
	
	cout << endl;
}
