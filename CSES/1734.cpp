#include <bits/stdc++.h> 
#define MAXN 200005
#define pii pair<int, int>
#define f first
#define s second

using namespace std;

int BIT[MAXN]; // BIT[idx]: 1 if unique, 0 if duplicate
int x[MAXN], ans[MAXN];
vector<pii> qrys[MAXN];
map<int, int> lb; // current leftmost indexes of values

void add(int idx, int val) {
	while(idx < MAXN) {
		BIT[idx] += val;
		idx += idx & -idx;
	}
}

int get(int idx) {
	int sum = 0;
	while(idx > 0) {
		sum += BIT[idx];
		idx -= idx & -idx;
	}
	return sum;
}

int main() {
	int n, q;
	cin >> n >> q;
	
	for(int i = 1; i <= n; i++) cin >> x[i];
	for(int i = 1; i <= q; i++) {
		int a, b; cin >> a >> b;
		qrys[a].push_back({b, i});
	}
	
	for(int i = n; i >= 1; i--) { // left edge of range taken into account
		// to take into account ALL unique values, push 1's as far left as possible
		// no matter how narrow the query is, the BIT will work
		int curr = x[i];
		if(lb.count(curr)) add(lb[curr], -1); 
		lb[curr] = i;

		add(i, 1);
		for(pii j : qrys[i]) ans[j.s] = get(j.f);
	}
	
	for(int i = 1; i <= q; i++) cout << ans[i] << endl;
}

