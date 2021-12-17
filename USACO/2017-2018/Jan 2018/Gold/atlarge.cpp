#include <bits/stdc++.h>

using namespace std;

const int MAXN = 100001;

vector<int> adj[MAXN];
int depth[MAXN], par[MAXN];
vector<int> leaves;
bool visited[MAXN];

bool comparator(const int a, const int b) { return depth[a] <= depth[b]; }

void dfs(int node) {
	depth[node] = depth[par[node]] + 1;
	for(int to : adj[node])
		if(to != par[node]) {
			par[to] = node;
			dfs(to);
		}
		
	if(adj[node].size() == 1) leaves.push_back(node);
}

int main() {
	ifstream cin("atlarge.in");
	ofstream cout("atlarge.out");
	
	int N, K;
	cin >> N >> K;
	
	for(int i = 0; i < N-1; i++) {
		int a, b;
		cin >> a >> b;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	
	dfs(K);
	sort(leaves.begin(), leaves.end(), comparator);
	
	int res = 0;
	for(int leaf : leaves) {
		bool leafNeeded = true;
		for(int curr = leaf; curr != 0; curr = par[curr]) {
			if(visited[curr]) {
				leafNeeded = false;
				break;
			}
			if(depth[leaf] - depth[curr] <= (depth[leaf]-1) / 2) visited[curr] = true;
		}
		
		if(leafNeeded) res++;
	}
	
	cout << res << endl;
}
