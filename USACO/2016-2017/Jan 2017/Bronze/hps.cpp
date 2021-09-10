#include <bits/stdc++.h>

using namespace std;

int moves[100][2];
int N;

int getHighScore() {
	int count1 = 0; // 1 > 2 > 3 > 1
	for(int m = 0; m < N; m++) {
		if(moves[m][0] == 1 && moves[m][1] == 2) count1++;
		else if(moves[m][0] == 2 && moves[m][1] == 3) count1++;
		else if(moves[m][0] == 3 && moves[m][1] == 1) count1++;
	}
	
	int count2 = 0; // 1 > 3 > 2 > 1
	for(int m = 0; m < N; m++) {
		if(moves[m][0] == 1 && moves[m][1] == 3) count2++;
		else if(moves[m][0] == 3 && moves[m][1] == 2) count2++;
		else if(moves[m][0] == 2 && moves[m][1] == 1) count2++;
	}
	
	return max(count1, count2);
}

int main() {
	ifstream cin("hps.in");
	ofstream cout("hps.out");
	
	cin >> N; // number of rounds
	
	for(int i = 0; i < N; i++) {
		cin >> moves[i][0]; // player 1
		cin >> moves[i][1]; // player 2
	}
	
	// get the highest possible score by changing the meaning of 1, 2, 3
	int score = getHighScore();
	cout << score << endl;
}
