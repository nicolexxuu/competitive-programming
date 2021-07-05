// Counting Haybales
#include <bits/stdc++.h>
#define ll long long

using namespace std;
int arr[200000];


struct SegTree {
	int N;
	ll *mins, *sums, *lazy;
	
	SegTree(int _N) {
		N = 1 << (int)(ceil(log2(_N)));
		mins = new ll[N * 2];
		sums = new ll[N * 2];
		lazy = new ll[N * 2];
		
		memset(mins, 0, 2 * N * sizeof(ll));
		memset(sums, 0, 2 * N * sizeof(ll));
        memset(lazy, 0, 2 * N * sizeof(ll));
		
		// build segtree
		for(int i = 0; i < _N; i++) {
			mins[N + i] = arr[i];
			sums[N + i] = arr[i];
		}
		
		for(int i = N - 1; i > 0; i--) {
			mins[i] = min(mins[i * 2], mins[i * 2 + 1]);
			sums[i] = sums[i * 2] + sums[i * 2 + 1];
		}
	}
	
	void prop(int node, int s, int e, int mid) {
		mins[2 * node] += lazy[node];
		mins[2 * node + 1] += lazy[node];
		sums[2 * node] += lazy[node] * (mid - s);
		sums[2 * node + 1] += lazy[node] * (e - mid);
		lazy[2 * node] += lazy[node];
		lazy[2 * node + 1] += lazy[node];
		lazy[node] = 0;
	}
	
	void update(int node, int s, int e, int lo, int hi, ll x) {
		if(e <= lo || s >= hi) return;
		if(s >= lo && e <= hi) { // be lazy
			mins[node] += x;
			sums[node] += (e-s) * x;
			lazy[node] += x;
			return;
		}
		
		int mid = (s + e) >> 1;
		prop(node, s, e, mid);
		update(2 * node, s, mid, lo, hi, x);
		update(2 * node + 1, mid, e, lo, hi, x);
		
		mins[node] = min(mins[2 * node], mins[2 * node + 1]);
		sums[node] = sums[2 * node] + sums[2 * node + 1];
	}
	
	void update(int lo, int hi, ll x) {
		update(1, 0, N, lo, hi, x);
	}
	
	ll queryMin(int node, int s, int e, int lo, int hi) {
		if(e <= lo || s >= hi) return LONG_MAX;
		if(s >= lo && e <= hi) return mins[node];
		
		
		int mid = (s + e) >> 1;
		prop(node, s, e, mid);
		return min(queryMin(node * 2, s, mid, lo, hi), queryMin(node * 2 + 1, mid, e, lo, hi));
	}
	
	ll queryMin(int lo, int hi) {
		return queryMin(1, 0, N, lo, hi);
	}
	
	ll querySum(int node, int s, int e, int lo, int hi) {
		if(e <= lo || s >= hi) return 0;
		if(s >= lo && e <= hi) return sums[node];
		
		int mid = (s + e) >> 1;
		prop(node, s, e, mid);
		return querySum(node * 2, s, mid, lo, hi) + querySum(node * 2 + 1, mid, e, lo, hi);
	}
	
	ll querySum(int lo, int hi) {
		return querySum(1, 0, N, lo, hi);
	}
};

int main() {
	int N, Q;
	cin >> N >> Q;
	
	for(int i = 0; i < N; i++) cin >> arr[i];
	SegTree st(N);
	

	for(int i = 0; i < Q; i++) {
		char ch;
		cin >> ch;
		
		int a, b;
		cin >> a >> b;
		
		if(ch == 'M') {
			cout << st.queryMin(a-1, b) << endl;
		} else if(ch == 'S') {
			cout << st.querySum(a-1, b) << endl;
		} else {
			int c;
			cin >> c;
			st.update(a-1, b, c);
		}
	
	}

}
	
