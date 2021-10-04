#include <bits/stdc++.h>

using namespace std;

vector<string> traits[100];

int main() {
	ifstream cin("guess.in");
	ofstream cout("guess.out");
	
	int N; 
	cin >> N;
	
	for(int i = 0; i < N; i++) {
		string name;
		cin >> name;
		
		int K;
		cin >> K;
		for(int j = 0; j < K; j++) {
			string trait;
			cin >> trait;
			traits[i].push_back(trait);
		}
	}
	
	int maxShared = 0;
	for(int i = 0; i < N; i++) {
		for(int j = i+1; j < N; j++) {
			int shared = 0;
			for(string t1 : traits[i]) {
				for(string t2 : traits[j]) {
					if(t1 == t2)
					shared++;
				}
			}
			maxShared = max(maxShared, shared);
		}
	}
	
	cout << maxShared + 1 << endl;
}
