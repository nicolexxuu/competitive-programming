#include <bits/stdc++.h>
#define ll long long
#define f first
#define s second
using namespace std;

int n;
ll x[200001];

// solution 1: find leftmost and rightmost value that accomodate a[i]
ll solve1() { 
	vector<ll> mxa(n);
	stack<int> stack;
	
	// to left of i
	for(int i = 0; i < n; i++) {
		while(stack.size() && x[stack.top()] >= x[i]) stack.pop();
		mxa[i] += x[i] * (i - (stack.size() ? stack.top() : -1));
		stack.push(i);
	}
	
	while(stack.size()) stack.pop();
	
	// to right of i
	for(int i = n-1; i >= 0; i--) {
		while(stack.size() && x[stack.top()] >= x[i]) stack.pop();
		mxa[i] += x[i] * ((stack.size() ? stack.top() : n) - i - 1);
		stack.push(i);
	}
	
	return *max_element(mxa.begin(), mxa.end());
}

// solution 2: 
ll solve2() {
	ll mxa = 0;
	stack<pair<ll, int>> stack; // {x, i}
	
	for(int i = 0; i < n+1; i++) {
		int start = i; // first index that can be included in i's rectangle
		while(stack.size() && stack.top().f > x[i]) { // don't worry about ties !
			pair<ll, int> curr = stack.top(); stack.pop();
			mxa = max(mxa, curr.f * (i - curr.s));
			start = curr.s;
		}
		
		stack.push({x[i], start});
	}
	
	return mxa;
}

int main() {
	cin >> n;
	for(int i = 0; i < n; i++) cin >> x[i];
	
//	cout << solve1() << endl;
	cout << solve2() << endl;
}
