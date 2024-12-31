#include <bits/stdc++.h>

#define ll long long
#define f first
#define s second

using namespace std;

ll p[200000], s[200000], res[200000];

ll calc_num(pair<int, int> a) {
	ll speed_sum = s[a.f] + s[a.s];
	return ((p[a.s]-p[a.f]+speed_sum-1)/speed_sum)*2 - (a.f%2 == 0);
}

int main() {
	int T; cin >> T;
	while(T--) {
		int N; cin >> N;
		for(int i = 0; i < N; i++) cin >> p[i];
		for(int i = 0; i < N; i++) cin >> s[i];
		
		priority_queue<pair<ll, pair<int, int>>> pq;
		
		for(int i = 0; i < N; i++) res[i] = 0;
		pair<int, int> adj[N];
		for(int i = 0; i < N; i++) {
			adj[i] = {i-1, i+1};
			if(i < N-1) pq.push({-calc_num({i, i+1}), {i, i+1}});
		}
		
		while(pq.size()) {
			ll num = -pq.top().f;
			pair curr = pq.top().s;
			pq.pop();
			if(res[curr.f] || res[curr.s]) continue;
			
			res[curr.f] = res[curr.s] = num;
			int l = adj[curr.f].f, r = adj[curr.s].s;
			if(l >= 0 && r < N) {
				adj[l].s = r;
				adj[r].f = l;
				pq.push({-calc_num({l, r}), {l, r}});
			}
		}
		
		for(int i = 0; i < N; i++) cout << res[i] << " \n"[i == N - 1];
	}
}
