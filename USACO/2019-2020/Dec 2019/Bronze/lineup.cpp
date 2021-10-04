#include <bits/stdc++.h>

using namespace std;

struct Cow {
	string name;
	vector<int> beside;
};

Cow cows[8];

int find(string n) {
	for(int i = 0; i < 8; i++) {
		if(cows[i].name == n) {
			return i;
		}
	}
	return -1;
}

int main() {
	ifstream cin("lineup.in");
	ofstream cout("lineup.out");
	
	cows[0].name = "Beatrice";
	cows[1].name = "Belinda";
	cows[2].name = "Bella";
	cows[3].name = "Bessie";
	cows[4].name = "Betsy";
	cows[5].name = "Blue";
	cows[6].name = "Buttercup";
	cows[7].name = "Sue";
	
	int N; cin >> N;
	for(int i = 0; i < N; i++) {
		string name1, name2;
		cin >> name1;
		string skip;
		cin >> skip >> skip >> skip >> skip;
		cin >> name2;
		
		int id1 = find(name1), id2 = find(name2);
		cows[id1].beside.push_back(id2);
		cows[id2].beside.push_back(id1);
	}
	
	vector<int> result;
	
	while(result.size() < 8) {
		int id = 0;
		while(count(result.begin(), result.end(), id) > 0 || cows[id].beside.size() > 1) {
			id++;
		}
		
		bool keepAdding = true;
		while(keepAdding) {
			result.push_back(id);
			keepAdding = false;
			
			for(int i : cows[id].beside) {
				if(count(result.begin(), result.end(), i) == 0) {
					id = i;
					keepAdding = true;
					break;
				}
			}
		}
	}
	
	for(int i : result) {
		cout << cows[i].name << endl;
	}
}
