#include <bits/stdc++.h>
#define MAXN 200003

using namespace std;

int n;
int BIT[MAXN], x[MAXN];

void update(int idx, int val) {
	while(idx < MAXN) {
		BIT[idx] += val;
		idx += idx & -idx;
	}
}

int query(int idx) {
	int sum = 0;
	while(idx > 0) {
		sum += BIT[idx];
		idx -= idx & -idx;
	}
	
	return sum;
}

int binarySearch(int target) {
	int l = 1, h = n;
	while(l < h) {
		int m = (l + h) / 2;
		int sum = query(m), open = sum - query(m-1);
		if(sum > target) {
			h = m - 1;
		} else if(sum < target) {
			l = m + 1;
		} else {
			if(open) return m;
			h = m - 1;
		}
	}
	
	return l;
}

int main() {
	cin >> n;
	for(int i = 1; i <= n; i++) {
		cin >> x[i];
		update(i, 1);
	}
	
	for(int i = 1; i <= n; i++) {
		int p; cin >> p;
		int idx = binarySearch(p);
		cout << x[idx] << endl;
		update(idx, -1);
	}
}
