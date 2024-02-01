#include <bits/stdc++.h>

using namespace std;

long long a[25000], b[25000], ad[25001], bd[25001];

int main() {
	ifstream cin("fencedin.in");
	ofstream cout("fencedin.out");
	int A, B, n, m;
	cin >> A >> B >> n >> m;
	
	for(int i = 0; i < n; i++) cin >> a[i];
	sort(a, a+n);
	for(int i = 0; i < m; i++) cin >> b[i];
	sort(b, b+m);
	
	ad[0] = a[0];
	for(int i = 0; i < n-1; i++) ad[i+1] = a[i+1] - a[i];
	ad[n] = A - a[n-1];
	bd[0] = b[0];
	for(int i = 0; i < m-1; i++) bd[i+1] = b[i+1] - b[i];
	bd[m] = B - b[m-1];
	
	sort(ad, ad+n+1); 
	sort(bd, bd+m+1);
	
	int ap = 1, bp = 1;
	long long res = ad[0] * m + bd[0] * n;
	while(ap <= n && bp <= m) {
		if(ad[ap] < bd[bp]) res += ad[ap++] * (m-bp+1);
		else res += bd[bp++] * (n-ap+1);
	}
	
	cout << res << endl;
}

