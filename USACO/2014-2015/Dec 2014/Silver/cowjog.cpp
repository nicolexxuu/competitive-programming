#include <vector>
#include <iostream>

using namespace std;
typedef long long ll;
int main () {
	ll N, T;
	cin >> N >> T;
	vector<ll> A;
	
	for(int i = 0; i < N; i++) {
		ll p, s;
		cin >> p >> s;
		A.push_back(p + s*T);
	}
	
	int res = 1;
	for(int i = N-2; i >= 0; i--) {
		if(A[i] >= A[i+1])	A[i] = A[i+1];
		else res++;
	}
	
	cout << res << '\n';
}	
