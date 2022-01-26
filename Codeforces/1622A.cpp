#include <bits/stdc++.h>

using namespace std;

int main() {
	cin.tie(0)->sync_with_stdio(0);
	int t; cin >> t;
	
	while(t--) {
		int l1, l2, l3;
		cin >> l1 >> l2 >> l3;
		
		if(l1 == l2 + l3 || l2 == l1 + l3 || l3 == l1 + l2) cout << "YES\n";
		else if((l1 % 2 == 0 && l2 == l3) || (l2 % 2 == 0 && l1 == l3) || (l3 % 2 == 0 && l2 == l1)) cout << "YES\n";
		else cout << "NO\n";
	}
}

