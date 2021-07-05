// Optimal Milking
#include <bits/stdc++.h>

using namespace std;
#define ll long long

struct node{
	ll mx, l, r, both;
};

const int sz = 1 << 17; // around 10^5
node t[2*sz];

node combine(node l, node r){
	node ret;
	ret.mx = max(l.mx + r.l, l.r + r.mx); // mx: overall max amount in segment, possibly including right and left endpoints
	ret.l = max(l.both + r.mx, l.l + r.l); // l: max amount in segment excluding left endpoint
	ret.r = max(l.mx + r.both, l.r + r.r); // r: max amount in segment excluding right endpoint
	ret.both = max(l.both + r.r, l.l + r.both); // both: max amount in segment excluding both endpoints
	return ret;
}

// change element pos of arr to val - update value at i in t
void update(int pos, int tl, int tr, ll val, int i){
	if(pos > tr || pos < tl) return;
	if(tl == tr){
		t[i].mx = val;
	} else {
		int tm = (tl+tr)/2;
		update(pos, tl, tm, val, i*2); 
		update(pos, tm+1, tr, val, i*2+1);
		t[i] = combine(t[i*2], t[i*2+1]);
	}
}

int main() {
	ifstream cin("optmilk.in");
	ofstream cout("optmilk.out");
	int N, Q; 
	cin >> N >> Q;
	
	// build tree from bottom up
	for(int i = 0; i < N; i++) cin >> t[sz+i].mx;
	// move up the segment tree and calculate values for parent nodes
	for (int i = sz - 1; i > 0; i--) t[i] = combine(t[i*2], t[i*2+1]);
	
	ll res = 0;
	for(int j = 0; j < Q; j++){
		int i; ll m; 
		cin >> i >> m;
		update(i-1, 0, sz-1, m, 1);
		// query segment tree for max amount in the whole array
		res += t[1].mx;
	}
	
	cout << res << endl;
}
