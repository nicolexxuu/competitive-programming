#include <bits/stdc++.h>

using namespace std;

string genomes[2][500];

int main() {
	ifstream cin("cownomics.in");
	ofstream cout("cownomics.out");
	
	int N, M;
	cin >> N >> M;
	
	for(int i = 0; i < N; i++) cin >> genomes[0][i];
	for(int i = 0; i < N; i++) cin >> genomes[1][i];
	
	int lo = 1, hi = M;
	while(lo < hi) {
		int mid = (lo + hi) / 2;
		bool good = false;
		
		for(int start = 0; start < M-1-mid; start++) {
			bool ok = true;
			unordered_set<string> spotty;
			
			for(int i = 0; i < N; i++) spotty.insert(genomes[0][i].substr(start, mid));
			for(int i = 0; i < N; i++) ok &= (spotty.find(genomes[1][i].substr(start, mid)) == spotty.end());
			good |= ok;
		}
		
		if(good) hi = mid;
		else lo = mid + 1;
	}
	
	cout << lo << endl;
}
