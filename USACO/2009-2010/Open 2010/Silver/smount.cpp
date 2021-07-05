#import <iostream>
#import <algorithm>
using namespace std;

int A[100000], rise[100000], fall[100000];
int N;

int main () {
	cin >> N;
	for(int i = 0; i < N; i++) {
		cin >> A[i];
	}
	
	rise[0] = 1, fall[N-1] = 1;
	for(int i = 1; i < N; i++) {	
		if(A[i] >= A[i-1]) rise[i] = rise[i-1] + 1;
		else rise[i] = 1;
	}
	
	for(int i = N-2; i >= 0; i--) {
		if(A[i] >= A[i+1]) fall[i] = fall[i + 1] + 1;
		else fall[i] = 1;
	}
	
	int mx = 0;
	for(int i = 0; i < N; i++) {
		mx = max(mx, fall[i] + rise[i] - 1);
	}
	cout << mx;
}
