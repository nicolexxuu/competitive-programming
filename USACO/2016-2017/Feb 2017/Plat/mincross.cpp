// Why Did the Cow Cross the Road

#include <iostream>
#include <algorithm>
#include <vector>

#define MAXN 100005
#define ll long long

using namespace std;

int BIT[MAXN];

void upd(int idx, int add) {
	while(idx < MAXN) {
		BIT[idx] += add;
		idx += idx & -idx;
	}
}

int get(int idx) {
	int sum = 0;
	while(idx > 0) {
		sum += BIT[idx];
		idx -= idx & -idx;
	}
	
	return sum;
}

bool comp(vector<int> a, vector<int> b) { return a[0] < b[0]; }

int main() {
	int N; cin >> N;
	
	int start[N+1];
	vector<vector<int>> loc(N+1);
	
	for(int i = 1; i <= N; i++) { 
		int a;
		cin >> a;
		start[a] = i;
	}
	
	loc[0].push_back(-1);
	for(int i = 1; i <= N; i++) {
		int a;
		cin >> a;
		loc[start[a]].push_back(i);
		loc[start[a]].push_back(start[a]);
	}
	
	vector<vector<int>> sortedLoc = loc;
	sort(sortedLoc.begin(), sortedLoc.end(), comp);
	
	// update BIT
	ll intersections = 0;
	
	for(int i = N; i >= 1; i--) {
		int from = sortedLoc[i][1];
		intersections += get(from-1);
		upd(from, 1);
	}
	
	ll minI = intersections;
	// shift 2nd row
	for(int i = 1; i <= N; i++) {
		intersections -= sortedLoc[i][1] - 1;
		intersections += N - sortedLoc[i][1];
		
		minI = min(minI, intersections);
	}
	
	// shift 1st row
	for(int i = 1; i <= N; i++) {
		intersections -= loc[i][0] - 1;
		intersections += N - loc[i][0];
		
		minI = min(minI, intersections);
	}
	
	cout << minI << endl;
}

