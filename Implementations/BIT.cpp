// Binary Index Tree Implementation

#include <bits/stdc++.h>

#define MAXN 10003

using namespace std;

int BIT[MAXN]; // 1-indexed

// update prefix sums of an array
// O(N log N)
void add(int idx, int val) {
	// -idx = !idx + 1 => 2's complement
	// idx & -idx => rightmost 1
	while(idx < MAXN) {
		BIT[idx] += val;
		idx += idx & -idx;
	}
}

// get prefix sum of array at position idx
// O(N log N)
int get(int idx) {
	int sum = 0;
	
	while(idx > 0) {
		sum += BIT[idx];
		idx -= idx & -idx;
	}
	
	return sum;
}

int main() {
	int N; cin >> N;
	int A[N];
	
	for(int i = 1; i <= N; i++) {
		cin >> A[i-1];
		add(i, A[i-1]);
	}
	
	cout << get(3) << " " << get(1) << endl;
}
