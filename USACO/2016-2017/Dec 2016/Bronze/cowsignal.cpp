#include <bits/stdc++.h>

using namespace std;

int main() {
	ifstream cin("cowsignal.in");
	ofstream cout("cowsignal.out");
	
	int M; // number of signals (strings)
	int N; // length of each signal / string
	int K; // number of copies to duplicate
	cin >> M >> N >> K;
	
	// amplify the signal
	for(int i = 0; i < M; i++) {
		string str;
		cin >> str;
		
		for(int j = 0; j < K; j++) { // write signal K times
			for(char c : str) {
				for(int j = 0; j < K; j++) { // each letter repeats K times
					cout << c;
				}
			}
			cout << endl;
		}
		
	}
}
