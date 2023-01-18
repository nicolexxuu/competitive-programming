#include <bits/stdc++.h>

using namespace std;

int N;
int p[200001], pos[200001];

void solve1() {
	vector<int> prv(N+1);
	vector<bool> hilo(N+1);
	
	stack<int> stack;
	stack.push(0);
	for(int i = N; i > 0; i--) {
		while(stack.top() > pos[i]) stack.pop();
		prv[pos[i]] = stack.top();
		stack.push(pos[i]);
	}
	
	while(stack.size() > 1) stack.pop(); // reset to just one element: 0

	int ans = 0;
	for(int i = 0; i <= N; i++) {
		while(stack.top() > pos[i]) {
			ans -= hilo[stack.top()];
			stack.pop();
		}
		
		hilo[pos[i]] = prv[pos[i]] && (!stack.top() || prv[stack.top()] != prv[pos[i]]);
		ans += hilo[pos[i]];
		cout << ans << endl;
		
		stack.push(pos[i]);
	}
}


void solve2() {
	vector<int> cnt(N+1);
	map<int, int> visited;
	
	for(int i = 1; i <= N; i++) {
		int x = p[i];
		auto it = visited.upper_bound(x);
		if(it != visited.end()) {
			if(it == visited.begin() || it->second > prev(it)->second) cnt[x]++;
		}
		
		if(i < pos[x-1]) cnt[x]--; // undo the previous hilo
		
		visited[x] = i;
	}

	int res = 0;
	for(int i = 0; i <= N; i++) {
		res += cnt[i];
		cout << res << endl;
	}
}

int main() {
	cin >> N;
	
	for(int i = 1; i <= N; i++) { 
		cin >> p[i];
		pos[p[i]] = i;
	}
	
//	solve1(); // monotonic stack: O(N)
	solve2(); // treemap: O(N log N)
}
