#include <bits/stdc++.h>
#define ll long long

using namespace std;
int n, m;
int arr[200000];

struct SegTree {
	int sz;
	ll *tree;
	
	SegTree(int N) {
		sz = 1 << (int)(ceil(log2(N)));
		tree = new ll[sz * 2];
		memset(tree, 0, 2 * sz * sizeof(ll));
		
		for(int i = 0; i < N; i++) tree[sz + i] = arr[i]; 
		for(int i = sz - 1; i > 0; i--) tree[i] = max(tree[i * 2], tree[i * 2 + 1]); 
	}
	
	void update(int idx, ll x) {
		idx += sz;
		tree[idx] += x; 
		for(int i = idx / 2; i > 0; i /= 2) tree[i] = max(tree[i * 2], tree[i * 2 + 1]);
	}
	
	int query(int r) {
		int idx = 1;
		while(idx < sz) {
			if(tree[2*idx] >= r) idx = idx * 2;
			else idx = idx * 2 + 1;
		}
		
		return min(n-1, idx - sz); // prevent OOB error
	}
};

int main() {
	cin >> n >> m;
	for(int i = 0; i < n; i++) cin >> arr[i];
	
	SegTree st(n);
	
	for(int i = 0; i < m; i++) {
		int r; cin >> r;
		int lo = st.query(r);
		if(arr[lo] < r) {
			cout << 0 << endl;
		} else {
			cout << lo+1 << endl;
			st.update(lo, -r);
			arr[lo] -= r;
		}
	}
}
