#include <bits/stdc++.h>
#define ll long long
using namespace std;

int binpow(ll a, ll b, ll m) {
	a %= m;
	ll res = 1;
	while(b > 0) {
		if(b % 2 == 1) res = res * a % m;
		a = a * a % m;
		b /= 2;
	}
	
	return res;
}

int main() {
	int n; cin >> n;
	while(n--) {
		ll a, b, c;
		cin >> a >> b >> c;
		
		cout << binpow(a, binpow(b, c, 1e9+6), 1e9+7) << endl;
	}
}
