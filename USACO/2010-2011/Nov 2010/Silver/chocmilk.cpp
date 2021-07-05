#include <iostream>
#include <algorithm>

using namespace std;

int N;
int in[100000], out[100000];

int main () {
	cin >> N;
	
	for(int i = 0; i < N-1; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		in[b]++;
		out[a]++;
	}
	
	int mn = 1, mx = N-1;
	
	for(int node = N-1; node >= 0; node--) {
		if(out[node] >= 2) mx = min(mx, node);
		if(in[node] >= 2 && mn == 1) mn = node;
	}
	
	for(int i = mn+1; i<= mx+1; i++) cout << i << endl;
}
