#include <bits/stdc++.h>
#define MAXN 100000

using namespace std;

vector<int> radj[MAXN];
int outDegree[MAXN];

int main() {
	int N, M;
	cin >> N >> M;
	
	for(int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		radj[b].push_back(a);
		outDegree[a]++;
	}
	
	priority_queue<int> toVisit;
	for(int i = 0; i < N; i++) if(outDegree[i] == 0) toVisit.push(i);
	
	vector<int> res;
	while(toVisit.size() > 0) {
		int curr = toVisit.top();
		toVisit.pop();
		
		res.push_back(curr);
		for(int from: radj[curr]) {
			outDegree[from]--;
			if(outDegree[from] == 0) toVisit.push(from);
		}
	}
	
	reverse(res.begin(), res.end());
	for(int i : res) cout << (i+1) << " ";
}
