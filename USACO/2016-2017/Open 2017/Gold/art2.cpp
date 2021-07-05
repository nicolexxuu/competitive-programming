#include <iostream>
#include <algorithm>
#include <cstdio>
#include <stack>

using namespace std;


const int INF = 2000000000;
const int MAXN = 100005;

int color[MAXN], mn[MAXN], mx[MAXN];
int N;

int main () {
	freopen("art2.in", "r", stdin);
	
	scanf("%d", &N);
	fill(mn, mn + MAXN, INF);
	fill(mx, mx + MAXN, -INF);
	
	for(int i = 0; i <= N+1; i++) {
		if(i > 0 && i <= N) scanf("%d", &color[i]);
		mn[color[i]] = min(mn[color[i]], i);
		mx[color[i]] = max(mx[color[i]], i);
	}
	
	int res = 0;
	int count = 0;
	stack<int> curr;
	
	for(int i = 0; i <= N+1; i++) {

		int c = color[i];
		
		if(i == mn[c]) {
			curr.push(c);
			count++;
			res = max(res, count);
		}

		if(i == mx[c]) {
			count--;
			if(curr.top() != c) {
				res = 0;
				break;
			}
			
			curr.pop();
		}
	}
	
	cout << res-1 << '\n';
	freopen("art2.out", "w", stdout);
	cout << res-1 << '\n';
}
