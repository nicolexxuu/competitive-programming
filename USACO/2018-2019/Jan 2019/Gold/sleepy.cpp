#include <bits/stdc++.h>
#define MAXN 100005
using namespace std;

int BIT[MAXN];
int arr[MAXN];

void upd(int idx) {
	while(idx < MAXN) {
		BIT[idx]++;
		idx += idx & -idx;
	}
}

int query(int idx) {
	int res = 0;
	while(idx > 0) {
		res += BIT[idx];
		idx -= idx & -idx;
	}
	
	return res;
}

int main() {
	ifstream cin("sleepy.in");
	ofstream cout("sleepy.out");
	int N; cin >> N;
	for(int i = 0; i < N; i++) cin >> arr[i];
	
	int sorted = 1;
	upd(arr[N-1]);
	
	for(int i = N-2; i >= 0 && arr[i] < arr[i+1]; i--) {
		sorted++;
		upd(arr[i]);
	}
	
	cout << N - sorted << endl;

	for(int i = 0; i < N-  sorted; i++) {
		cout << N - sorted - i - 1 + query(arr[i]);
		if(i < N - sorted - 1) cout << " ";
		upd(arr[i]);
	}
	cout << endl;
}
