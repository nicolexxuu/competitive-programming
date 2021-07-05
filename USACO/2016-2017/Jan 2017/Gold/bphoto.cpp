#include <bits/stdc++.h>
#define MAXN 100003
#define pii pair<int, int>
#define f first
#define s second

using namespace std;

pii h[MAXN];
int BIT[MAXN];

bool operator <(pii p, pii q) {
	return p.f > q.f;
}

void upd(int idx) {
	while(idx < MAXN) {
		BIT[idx]++;
		idx += idx & -idx;
	}
}

int query(int idx) {
	int sum = 0;
	while(idx > 0) {
		sum += BIT[idx];
		idx -= idx & -idx;
	}
	return sum;
}

int main() {
	ifstream cin("bphoto.in");
	ofstream cout("bphoto.out");
	int N; cin >> N;
	
	for(int i = 0; i < N; i++) {
		cin >> h[i].f;
		h[i].s = i+1;
	}
	sort(h, h + N, greater<pii>());

	int cnt = 0;
	for(int i = 0; i < N; i++) {
		int l = query(h[i].s), r = query(N) - l;
		if(min(l, r) * 2 < max(l, r)) cnt++;
		upd(h[i].s);
	}
	cout << cnt << endl;
}
