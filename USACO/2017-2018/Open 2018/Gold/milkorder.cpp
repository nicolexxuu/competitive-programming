// Milking Order

#include <bits/stdc++.h>

using namespace std;

vector<int> ob[50000], adj[100000];
int inDegree[100000], cows[100000];
int N, M;

bool top_sort(int x) {
	
	for(int i = 0; i < 100000; i++) {
		inDegree[i] = 0;
		adj[i].clear();
		
		
	}
	
	for(int i = 0; i < x; i++) {
		for(int j = 1; j < ob[i].size(); j++) {
			inDegree[ob[i][j]]++;
			adj[ob[i][j-1]].push_back(ob[i][j]);
		}
	}
	
	priority_queue<int, vector<int>, greater<int>> toVisit;
	for(int i = 0; i < N; i++) if(inDegree[i] == 0) toVisit.push(i);
	
	int num = 0;
	while(toVisit.size() != 0) {
		int curr = toVisit.top();
		toVisit.pop();
		cows[num] = curr;
		for(int j : adj[curr]) {
			inDegree[j]--;
			if(inDegree[j] == 0) toVisit.push(j);
		}	
		num++;	
	}
	
	if(num != N) return false;
	return true;
}

int main() {
	ifstream cin("milkorder.in");
	ofstream cout("milkorder.out");
	
	cin >> N >> M;
	for(int i = 0; i < M; i++) {
		int a; cin >> a;
		for(int j = 0; j < a; j++) {
			int b; cin >> b; b--;
			ob[i].push_back(b);
		}
	}
	
	int lo = 0, hi = M;
	while(lo < hi) {
		int x = (lo + hi + 1) / 2;
		if(top_sort(x)) lo = x;
		else hi = x - 1;
	}
	
	top_sort(hi);
	for(int i = 0; i < N-1; i++) cout << cows[i] + 1 << " ";
	cout << cows[N-1] + 1 << endl;
}
