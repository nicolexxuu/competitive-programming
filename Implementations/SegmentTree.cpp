// Segment Tree (Range Sum) Implementation

#include <bits/stdc++.h>
#define ll long long

using namespace std;
int arr[200000];


struct SegTree {
	int sz;
	ll *tree;
	
	// build and initialize tree
	// O(N)
	SegTree(int N) {
		// sz = 1 << (int)(ceil(log2(N)));
		sz = N;
		tree = new ll[sz * 2];
		memset(tree, 0, 2 * sz * sizeof(ll));
		
		for(int i = 0; i < N; i++) tree[sz + i] = arr[i]; // original values
		for(int i = sz - 1; i > 0; i--) tree[i] = tree[i * 2] + tree[i * 2 + 1]; // calculate parent node
	}
	
	// add value x to element at position idx
	// O(log N)
	void update(int idx, ll x) {
		idx += sz;
		tree[idx] += x; // add value at idx
		for(int i = idx / 2; i > 0; i /= 2) tree[i] = tree[i * 2] + tree[i * 2 + 1]; // update parents
	}
	
	// get sum of element values within range [lo, hi)
	// O(log N)
	ll query(int lo, int hi) {
		lo += sz, hi += sz - 1;
		ll total = 0;
		
		while(lo <= hi) {
			if(lo % 2 == 1) // lo is a right child -> parent not included
				total += tree[lo++]; // move lo to the right
			if(hi % 2 == 0) // hi is a left child -> parent not included
				total += tree[hi--]; // move hi to the left
			
			// go to parents; move up the tree
			lo /= 2; 
			hi /= 2;
		}
	
		return total;
	}
};

int main() {
	int N, Q;
	
	cin >> N;
	for(int i = 0; i < N; i++) cin >> arr[i];
	SegTree st(N);
	
	cin >> Q;

	for(int i = 0; i < Q; i++) {
		char ch;
		int a, b;
		cin >> ch >> a >> b;
		
		if(ch == 'q') cout << st.query(a-1, b) << endl;
		else st.update(a-1, b);
	}

}
	
