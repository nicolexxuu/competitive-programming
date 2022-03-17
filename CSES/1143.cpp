#include <bits/stdc++.h>
#define ll long long

using namespace std;
int n, m;
int arr[200000];

struct SegTree {
	int sz;
	ll *tree;
	
	SegTree(int N) {
		sz = N;
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
	
	ll query(int lo, int hi) {
		lo += sz, hi += sz - 1;
		ll total = 0;
		
		while(lo <= hi) {
			if(lo % 2 == 1) 
				total = max(total, tree[lo++]); 
			if(hi % 2 == 0) 
				total = max(total, tree[hi--]); 
			
			lo /= 2; 
			hi /= 2;
		}
	
		return total;
	}
};

void solve1() {
	SegTree st(n);
	
	for(int i = 0; i < m; i++) {
		int r; cin >> r;
		
		int lo = 0, hi = n-1;
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (st.query(0, mid+1) >= r) hi = mid;
			else lo = mid + 1;
		}

		if(arr[lo] < r) {
			cout << 0 << endl;
		} else {
			cout << lo+1 << endl;
			st.update(lo, -r);
			arr[lo] -= r;
		}
	}
}

void solve2() {
}

int main() {
	cin >> n >> m;
	for(int i = 0; i < n; i++) cin >> arr[i];
	
//	solve1();
	solve2();
}
