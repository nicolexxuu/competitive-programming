#include <bits/stdc++.h>

using namespace std;

const int N = 2e5+3;
int n, q;
int t[N*2];

void build() {
	for(int i = n-1; i > 0; i--) t[i] = min(t[i<<1], t[i<<1|1]);
}

void upd(int k, int u) {
	t[k+=n] = u;
	while(k > 1) {
		t[k >> 1] = min(t[k], t[k^1]);
		k >>= 1;
	}
}

int qry(int a, int b) { // range [a, b)
	int res = 2e9;
	a += n; b += n;
	while(a < b) {
		if(a&1) res = min(res, t[a++]);
		if(b&1) res = min(res, t[--b]); // exclusive border
		a >>= 1; b >>= 1;
	}
	return res;
}

int main() {
	cin >> n >> q;
	for(int i = 0; i < N*2; i++) t[i] = 2e9;
	for(int i = 0; i < n; i++) cin >> t[i+n];
	
	build();
	
	for(int i = 0; i < q; i++) {
		cout << endl;
		int p, a, b;
		cin >> p >> a >> b;
		a--;
		if(p == 1) upd(a, b);
		else cout << qry(a, b) << endl;
	}
}
