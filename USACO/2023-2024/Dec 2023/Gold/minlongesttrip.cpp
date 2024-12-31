#include <bits/stdc++.h>
#define ll long long

using namespace std;

const int MAXN = 200005;

vector<pair<int, int>> adj[MAXN];
int outdeg[MAXN], len[MAXN], rnk[MAXN];
vector<vector<ll>> cand[MAXN];
ll sum[MAXN];

int main() {
	int N, M;
	cin >> N >> M;
	
	for(int i = 0; i < M; i++) {
		int a, b, l;
		cin >> a >> b >> l;
		a--; b--;
		outdeg[a]++;
		adj[b].push_back({a, l});
	}
	
	vector<int> to_visit;
	for(int i = 0; i < N; i++) 
		if(outdeg[i] == 0) to_visit.push_back(i);
		
	while(to_visit.size() > 0) {
		vector<int> nodes; 
		for(int v : to_visit) {
			for(pair<int, int> e : adj[v]) {
				auto [u, l] = e;
				outdeg[u]--;
				cand[u].push_back({-len[v], l, rnk[v], sum[v]});
				if(outdeg[u] == 0) {
					len[u] = len[v] + 1;
					nodes.push_back(u);
				}
			}
		}
		
		swap(nodes, to_visit);
		
		
		// determine next batch's ranks
		vector<vector<ll>> to_rank;
		for(int u : to_visit) {
			sort(cand[u].begin(), cand[u].end());
			sum[u] = cand[u][0][1] + cand[u][0][3];
			to_rank.push_back({cand[u][0][1], cand[u][0][2], u});
		}
		
		sort(to_rank.begin(), to_rank.end());
		for(int i = 0; i < to_rank.size(); i++) 
			rnk[to_rank[i][2]] = i;
	}
	
	
	for(int i = 0; i < N; i++) cout << len[i] << " " << sum[i] << endl;
}
