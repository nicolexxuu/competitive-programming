#include <iostream>
#include <set>
#include <algorithm>
#include <vector>

using namespace std;

const int INF = 2000000000;
int N, D;
int res = INF;
vector<pair<int, int>> drops;
multiset<int> inRange;

int main () {
	cin >> N >> D;
	for(int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;
		drops.push_back(make_pair(x, y));
	}
	
	sort(drops.begin(), drops.end());
	
	int l = 0, r = 0;
	inRange.insert(drops[0].second);
	multiset<int>::iterator it;
	
	while(1) {
		if(*inRange.rbegin() - *inRange.begin() < D) {
			if(++r == N) break;
			inRange.insert(drops[r].second);
		} else {
			if(drops[r].first - drops[l].first <= D) res = min(res, drops[r].first - drops[l].first);
			it = inRange.find(drops[l].second);
			inRange.erase(it);
			if(++l == N) break;
		}
	}
	if(res == INF) cout << -1 << endl;
	else cout << res << endl;
}


