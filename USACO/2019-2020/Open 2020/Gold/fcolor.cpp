#include <bits/stdc++.h>
#define MAXN 200005
#define pb push_back
 
using namespace std;

int N, M;
int arr[MAXN], sz[MAXN], clr[MAXN];
vector<int> lb[MAXN];
queue<int> to_visit;

void init() {
	for(int i = 1; i <= N; i++) {
		arr[i] = i;
		sz[i] = 1;
	}
}

int root(int a) {
	while(arr[a] != a) {
		arr[a] = arr[arr[a]];
		a = arr[a];
	}
	
	return a;
}

void merge(int a, int b) {
	int r_a = root(a), r_b = root(b); 
	if(r_a == r_b) return;
	if(sz[r_a] < sz[r_b]) swap(r_a, r_b);
	
	arr[r_b] = r_a;
	sz[r_a] += sz[r_b];
	
	lb[r_a].insert(end(lb[r_a]), begin(lb[r_b]), end(lb[r_b]));
	lb[r_b].clear();
	if(lb[r_a].size() > 1) to_visit.push(r_a);
}

int main() {
	ifstream cin("fcolor.in");
	ofstream cout("fcolor.out");
	cin >> N >> M;
	init();
	for(int i = 1; i <= M; i++) {
		int a, b; cin >> a >> b;
		lb[a].pb(b);
	}
	
	for(int i = 1; i <= N; i++) if(lb[i].size() > 1) to_visit.push(i);
	while(to_visit.size()) {
		int x = to_visit.front();
		if(lb[x].size() <= 1) { to_visit.pop(); continue; }
		int a = lb[x].back(); lb[x].pop_back();
		if(root(a) != root(lb[x].back())) merge(a, lb[x].back());
	}	
	
	int count = 1;
	for(int i = 1; i <= N; i++) {
		if(clr[root(i)] == 0) clr[root(i)] = count++;
		cout << clr[root(i)] << endl;
	}
}
