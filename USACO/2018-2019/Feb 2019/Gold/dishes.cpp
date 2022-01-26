#include <bits/stdc++.h>
#define MAXN 100005
using namespace std;

int N;
int id[MAXN];
vector<stack<int>> stacks(MAXN);

int main() {
	cin.tie(0)->sync_with_stdio(0);
	ifstream cin("dishes.in");
	ofstream cout("dishes.out");
	cin >> N;
	int mx = 0; // max plate added to clean stack
	
	for(int i = 0; i < N; i++) {
		int d; cin >> d;
		if(d < mx) {
			cout << i << endl;
			break;
		}
		
		for(int s = d; s > 0 && !id[s]; s--) id[s] = d;
		while(stacks[id[d]].size() && stacks[id[d]].top() < d) {
			// assume that stacks[i] for all i < id[d] are popped off
			mx = stacks[id[d]].top();
			stacks[id[d]].pop();
		}
		
		stacks[id[d]].push(d);
	}
}
