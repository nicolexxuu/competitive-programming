#include <bits/stdc++.h>

using namespace std;

int main() {
	ifstream cin("evolution.in");
	ofstream cout("evolution.out");
	
	int N;
	cin >> N;
	vector<string> pops[N];
	vector<string> allChars;
	
	for(int i = 0; i < N; i++) {
		int K;
		cin >> K;
		
		for(int j = 0; j < K; j++) {
			string characteristic;
			cin >> characteristic;
			pops[i].push_back(characteristic);
			allChars.push_back(characteristic);
		}
	}
	
	bool res = true;
	for(int i = 0; i < allChars.size(); i++) {
		for(int j = i+1; j < allChars.size(); j++) {
			string c1 = allChars[i], c2 = allChars[j];
			int c1Only = 0, c2Only = 0, both = 0;
			
			for(vector<string> arr : pops) {
				int c1Count = 0, c2Count = 0;
				for(string characteristic : arr) {
					if(characteristic == c1) c1Count++;
					if(characteristic == c2) c2Count++;
				}
				
				if(c1Count > 0 && c2Count > 0) both++;
				else if(c1Count > 0) c1Only++;
				else if(c2Count > 0) c2Only++;
			}
			
			if(c1Only > 0 && c2Only > 0 && both > 0) res = false;
		}
	}
	
	if(res) cout << "yes" << endl;
	else cout << "no" << endl;
}
