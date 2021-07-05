// Cow Traffic

#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;
int N, M;
vector<int> adj[5000], reversed[5000];
int in[5000], out[5000];

int main () {
	cin >> N >> M;
	for(int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		adj[a].push_back(b);
		reversed[b].push_back(a);
	}
	
	for(int i = 0; i < N; i++) {
		if(adj[i].size() == 0) out[i] = 1;
		if(reversed[i].size() == 0) in[i] = 1;
	}

	for(int i = 1; i < N; i++) {
		for(int from: reversed[i]) {
			in[i] += in[from];
		}
	}
	
	for(int i = N-2; i >= 0; i--) {
		for(int from: adj[i]) {
			out[i] += out[from];
		}
	}
	
	
	int res = 1;
	for(int from = 0; from < N; from++) {
		for(int to: adj[from]) {
			res = max(res, in[from] * out[to]);
		}
	}
	cout << res << endl;
}
