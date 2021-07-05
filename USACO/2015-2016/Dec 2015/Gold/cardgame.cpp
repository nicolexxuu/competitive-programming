// High Card Low Card (Gold)

#include <iostream>
#include <fstream>
#include <algorithm>
#include <set>

using namespace std;

int main() {
	ifstream cin("cardgame.in");
	ofstream cout("cardgame.out");
	int N;
	cin >> N;
	
	int e[N], b[N];
	set<int> seen;
	
	for(int i = 0; i < N; i++) {
		cin >> e[i];
		seen.insert(e[i]);
	}
	
	int idx = 0;
	for(int i = 1; i <= 2 * N; i++) {
		if(seen.find(i) == seen.end()) b[idx++] = i;
	}
	
	sort(b, b + N, greater<int>());
	sort(b + N/2, b + N);
	
	sort(e, e + N/2, greater<int>());
	sort(e + N/2, e + N);
	
	int res = 0;
	
	idx = 0;
	
	for(int i = 0; i < N/2 && idx < N/2; i++) {
		if(b[idx] > e[i]) {
			res++;
			idx++;
		}
	}
	
	idx = N/2;
	
	for(int i = N/2; i < N && idx < N; i++) {
		if(b[idx] < e[i]) {
			res++;
			idx++;
		}
	}
	
	cout << res << endl;
}
