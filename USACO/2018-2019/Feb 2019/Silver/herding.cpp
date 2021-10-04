#include <bits/stdc++.h>

using namespace std;

int L[100000];

int main() {
	ifstream cin("herding.in");
	ofstream cout("herding.out");
	int N;
	cin >> N;
	
	for(int i = 0; i < N; i++) {
		cin >> L[i];
	}
	
	sort(L, L+N);

	int mn = N;
	if((L[N-2]-L[0]+1 == N-1 && L[N-1]-L[N-2]-1 > 1) || (L[N-1]-L[1]+1 == N-1 && L[1]-L[0]-1 > 1)) {
		mn = 2;
	} else {
		for(int i = 0, j = 0; i < N; i++) {
			while(j < N-1 && L[j+1]-L[i]+1 <= N) {
				j++;
			}
			mn = min(mn, N-j+i-1);
		}
	}

	int mx = L[N-1] - L[0] + 1 - N;
	mx -= min(L[1]-L[0]-1, L[N-1]-L[N-2]-1);
	
	cout << mn << endl;
	cout << mx << endl;
}
