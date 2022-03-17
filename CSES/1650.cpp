#include <iostream>

#define MAXN 200005

using namespace std;

int BIT[MAXN];

void add(int idx, int val) {
	while(idx < MAXN) {
		BIT[idx] ^= val;
		idx += idx & -idx;
	}
}

int get(int idx) {
	int sum = 0;
	
	while(idx > 0) {
		sum ^= BIT[idx];
		idx -= idx & -idx;
	}
	
	return sum;
}

int main() {
	int n, q;
	cin >> n >> q;
	int x[n];
	
	for(int i = 1; i <= n; i++) {
		cin >> x[i-1];
		add(i, x[i-1]);
	}
	
	for(int i = 0; i < q; i++) {
		int a, b;
		cin >> a >> b;
		cout << (get(b) ^ get(a-1)) << endl;
	}
}
