#include <bits/stdc++.h>
#define ll long long
using namespace std;

int main() {
	cin.tie(0)->sync_with_stdio(0);
	int t; cin >> t;
	
	while(t--) {
		int n;
		ll k;
		cin >> n >> k;
		
		vector<ll> a(n);
		ll sum = 0;
		
		for(int i = 0; i < n; i++) {
			cin >> a[i];
			sum += a[i];
		}
		
		sort(a.begin(), a.end());
		if(sum <= k) {
			cout << 0 << '\n';
			continue;
		}
		
		ll best = sum - k;
		
		ll curr = 0;
		for(int i = n-1; i > 0; i--) {
			curr += a[i];
			ll moves = n - i;
			
			ll c = n - i + 1;
			ll subs = sum - curr + a[0] * (n - i) - k;
			if(subs > 0) moves += ceil(((double)(subs))/c);
			best = min(best, moves);
		}
		
		cout << best << '\n';
	}
}

