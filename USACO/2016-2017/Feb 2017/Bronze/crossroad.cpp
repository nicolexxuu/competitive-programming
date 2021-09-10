#include <bits/stdc++.h>

using namespace std;

int cows[10];

int main() {
	ifstream cin("crossroad.in");
	ofstream cout("crossroad.out");
	
	int N;
	cin >> N; // number of records
	fill(cows, cows+10, -1);
	
	// read the input and count the crossings
	int count = 0; 
	for(int i = 0; i < N; i++) {
		int cid, side;
		cin >> cid; // cow ID
		cin >> side; // current side
		cid--;
		
		if(cows[cid] != side && cows[cid] != -1) count++;
		cows[cid] = side;
	}
	
	cout << count << endl;
}
