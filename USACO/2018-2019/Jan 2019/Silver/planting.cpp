#include <bits/stdc++.h>

using namespace std;
int d[100001];

int main() {
	ifstream cin("planting.in");
	ofstream cout("planting.out");
	
	int N; cin >> N;
	for(int i = 0; i < N-1; i++) {
		int a, b; 
		cin >> a >> b;
		d[a]++; d[b]++;
	}
	
	int res = 0;
	for(int i : d) res = max(res, i);
	cout << res + 1 << endl;
}
