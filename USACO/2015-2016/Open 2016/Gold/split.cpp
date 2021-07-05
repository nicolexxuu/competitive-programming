#include <bits/stdc++.h>
#define pii pair<int, int>
#define f first
#define s second
#define ll long long

using namespace std;
int N;
ll total = 1, mn = LLONG_MAX;
vector<pii> cows;

void solve() {
	sort(cows.begin(), cows.end());
	total *= cows[N-1].f - cows[0].f;
	multiset<int> l, r;
	for(pii p: cows) r.insert(p.s);

	for(int border = 0; border < N; border++) {
		if(border > 0) mn = min(mn, (ll)(cows[border-1].f - cows[0].f) * (*l.rbegin() - *l.begin()) + (ll)(cows[N-1].f - cows[border].f) * (*r.rbegin() - *r.begin()));
		r.erase(r.lower_bound(cows[border].s));
		l.insert(cows[border].s);
	}
}

int main() {
	ifstream cin("split.in");
	ofstream cout("split.out");
	cin >> N;
	for(int i = 0; i < N; i++) {
		int a, b;
		cin >> a >> b;
		cows.push_back({a, b});
	}
	
	solve();
	for(pii& p: cows) swap(p.f, p.s);
	solve();
	
	cout << total - mn << endl;
}
