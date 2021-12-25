#include <bits/stdc++.h>

using namespace std;

int main() {
	int n; cin >> n;
	
	stack<pair<int, int>> curr; // {val, index}
	curr.push({0, 0});
	
	for(int i = 1; i <= n; i++) {
		int x; cin >> x;
		while(curr.top().first >= x) curr.pop();
		
		cout << curr.top().second << " ";
		curr.push({x, i});
	}
}

