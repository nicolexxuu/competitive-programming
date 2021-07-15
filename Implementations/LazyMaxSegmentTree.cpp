// Segment Tree (Range Maximum) Lazy Propagation Implementation

#include <bits/stdc++.h>
#define ll long long

using namespace std;
int arr[200000];


struct SegTree {
	int sz;
	ll *tree, *lazy;
	
	// build and initialize tree
	// O(N)
	SegTree(int N) {
		sz = 1 << (int)(ceil(log2(N)));
		tree = new ll[sz * 2];
		lazy = new ll[sz * 2];
		
		memset(tree, 0, 2 * sz * sizeof(ll));
        	memset(lazy, 0, 2 * sz * sizeof(ll));
		
		for(int i = 0; i < N; i++) tree[sz + i] = arr[i]; // original values
		for(int i = sz - 1; i > 0; i--) tree[i] = max(tree[i * 2], tree[i * 2 + 1]); // calculate parent node
	}
	
	// lazy propagation to children
	void prop(int node) {
		// include current lazy values
		tree[2 * node] += lazy[node];
		tree[2 * node + 1] += lazy[node];
		
		// mark children as lazy
		lazy[2 * node] += lazy[node];
		lazy[2 * node + 1] += lazy[node];
		lazy[node] = 0; // reset original node
	}
	
	// increment elements within range [lo, hi) by value x
	// O(log N)
	void update(int node, int s, int e, int lo, int hi, ll x) {
		if(e <= lo || s >= hi) return; // out of range
		if(s >= lo && e <= hi) { // completely contained; be lazy
			tree[node] += x;
			lazy[node] += x;
			return;
		}
		
		int mid = (s + e) >> 1;
		prop(node); // propagate lazy value to children
		
		// update children
		update(2 * node, s, mid, lo, hi, x); 
		update(2 * node + 1, mid, e, lo, hi, x);
		
		tree[node] = max(tree[2 * node], tree[2 * node + 1]); // update root
	}
	
	// helper function
	void update(int lo, int hi, ll x) {
		update(1, 0, sz, lo, hi, x);
	}
	
	// get max element value within range [lo, hi)
	// O(log N)
	ll query(int node, int s, int e, int lo, int hi) {
		if(e <= lo || s >= hi) return LONG_MIN; // out of range
		if(s >= lo && e <= hi) return tree[node]; // completely contained
		
		
		int mid = (s + e) >> 1;
		prop(node); // lazy propagation
		return max(query(node * 2, s, mid, lo, hi), query(node * 2 + 1, mid, e, lo, hi)); // return final result
	}
	
	// helper function
	ll query(int lo, int hi) {
		return query(1, 0, sz, lo, hi);
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
		
		if(ch == 'M') {
			int a, b, c;
			cin >> a >> b;
			cout << st.query(a-1, b) << endl;
		} else {
			int a, b, c;
			cin >> a >> b >> c;
			st.update(a-1, b, c);
		}
	
	}

}
	
