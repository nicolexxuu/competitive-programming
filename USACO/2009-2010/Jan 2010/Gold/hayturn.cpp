#include <iostream>
#include <algorithm>

using namespace std;

int main() {
	int N;
	cin >> N;
	
	int w[N];
	for(int i = 0; i < N; i++) cin >> w[i];
	
	long mf = 0, ms = 0;
	long pf = 0, ps = 0;
	
	for(int i = N-1; i >= 0; i--) {
		if(w[i] + ps >= pf) {
			long temp = pf;
			pf = w[i] + ps;
			ps = temp;
		}

		if(pf >= mf) {
			mf = pf;
			ms = ps;
		}
	}
	
	cout << mf << " " << ms << endl;
}
