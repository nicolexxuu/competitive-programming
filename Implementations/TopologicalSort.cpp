// Topological Sort Implementation (for DAGs)
// O(N)

#include <bits/stdc++.h>
#define MAXN 100000

using namespace std;

vector<int> adj[MAXN];
int inDegree[MAXN];

int main() {
	int N, M;
	cin >> N >> M;
	
	for(int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		adj[a].push_back(b);
		inDegree[b]++;
	}
	
	deque<int> toVisit;
	for(int i = 0; i < N; i++) if(inDegree[i] == 0) toVisit.push_back(i);
	
	// if iterations < N: graph is cyclic
	while(toVisit.size() > 0) {
		int curr = toVisit.front();
		toVisit.pop_front();
		
		cout << curr + 1 << endl;
		for(int to: adj[curr]) {
			inDegree[to]--;
			if(inDegree[to] == 0) toVisit.push_back(to);
		}
	}
	
}
