#include <bits/stdc++.h>
 
#define MAXN 400005
 
using namespace std;
 
int q[200000][3];
int BIT[MAXN]; 
vector<int> comp;
 
void add(int idx, int val) { 
	// use upper_bound to account to get compressed index (starts with 1)
	idx = upper_bound(comp.begin(), comp.end(), idx) - comp.begin();
	while(idx < MAXN) {
		BIT[idx] += val;
		idx += idx & -idx;
	}
}
 
int get(int idx) {
	idx = upper_bound(comp.begin(), comp.end(), idx) - comp.begin();
	int sum = 0;
	
	while(idx > 0) {
		sum += BIT[idx];
		idx -= idx & -idx;
	}
	
	return sum;
}
 
int main() {
	int N, Q;
	cin >> N >> Q;
	
	vector<int> values(N); 
	for(int i = 0; i < N; i++) cin >> values[i];
	
	for(int i = 0; i < Q; i++) {
		char t; 
		cin >> t >> q[i][1] >> q[i][2];
		q[i][0] = t == '!';
		
		if(q[i][0]) {
			q[i][1]--;
			values.push_back(q[i][2]);
		} 
	}
	
	comp = values;
	sort(comp.begin(), comp.end());
	comp.resize(unique(comp.begin(), comp.end()) - comp.begin());
 
	for(int i = 0; i < N; i++) add(values[i], 1);
	for(int i = 0; i < Q; i++) {
		if(q[i][0]) {
			add(values[q[i][1]], -1);
			values[q[i][1]] = q[i][2];
			add(q[i][2], 1);
		} else {
			cout << get(q[i][2]) - get(q[i][1]-1) << endl; 
		}
	}
}
