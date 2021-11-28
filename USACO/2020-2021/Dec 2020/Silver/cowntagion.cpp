#include <bits/stdc++.h>
#define pb push_back
using namespace std;

vector<int> adj[100000];
bool visited[100000];

int main() {
	int N; cin >> N;
	for(int i = 0; i < N-1; i++) {
		int a, b;
		cin >> a >> b;
		adj[a-1].pb(b-1);
		adj[b-1].pb(a-1);
	}
	
	int res = N-1;
	for(int i = 0; i < N; i++) {
		int n = adj[i].size() + ((i == 0) ? 1 : 0);
		if(n == 0) continue;
		res += ceil(log2(n));
	}
	
	cout << res << endl;
}
