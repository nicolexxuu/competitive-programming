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
	
	vector<int> res;
	while(toVisit.size() > 0) {
		int curr = toVisit.front();
		toVisit.pop_front();
		
		res.push_back(curr+1);
		for(int to: adj[curr]) {
			inDegree[to]--;
			if(inDegree[to] == 0) toVisit.push_back(to);
		}
	}
	
	if(res.size() != N) cout << "IMPOSSIBLE" << endl;
	else for(int i : res) cout << i << " ";
	
}
