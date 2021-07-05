#include <iostream>
#include <queue>

using namespace std;
int D[10000];
int N, T;

bool finish (int size) {
	priority_queue<int, vector<int>, greater<int>> stage;
	int last = 0;
	
	// load onto stage
	while (last < size && last < N) {
		stage.push(D[last]);
		last++;
	}
	
	while(!stage.empty()) {
		int curr = stage.top();
		stage.pop();
		if(curr > T) return false;
		
		if(last < N) {
			stage.push(D[last] + curr);
			last++;
		}
	}
	
	return true;
}

int main () {
	freopen("cowdance.in", "r", stdin);
	
	cin >> N >> T;
	
	for(int i = 0; i < N; i++) cin >> D[i];
	
	int lo = 1, hi = T;
	while (lo < hi) {
		int mid = (lo + hi)/2;
		if (finish(mid))hi = mid;
		else lo = mid + 1;
	}
	
	freopen("cowdance.out", "w", stdout);
	cout << lo << '\n';
}
