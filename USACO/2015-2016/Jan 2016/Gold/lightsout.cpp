#include <iostream>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

int N;
int x[205], y[205];
int cost[205];

int main (){
	cin >> N;
	for(int i = 0; i < N; i++) cin >> x[i] >> y[i];
	
	// -1 for clockwise, -2 for counterclockwise
	vector<int> S; //?
	S.push_back(0);
	for(int i = 0; i < N; i++) {
		int j = (i+1)%N;
		S.push_back(abs(y[i] - y[j]) + abs(x[i] - x[j]));
		
		int k = (j+1)%N;
		if((x[i] - x[j]) * (y[k] - y[j]) - (x[k] - x[j]) * (y[i] - y[j]) > 0)// right turn 
			S.push_back(-1);
		else
			S.push_back(-2);
	}
	S.back() = 0;
	
	for(int i = 0; i < N-1; i++)
		cost[i+1] = cost[i] + S[i*2+1];
	
	for(int i = N-1; i > 0; i--)
		cost[i] = min(cost[i], cost[i+1] + S[2*i+1]);
		
	// for(int i = 0; i <= N; i++) cout << cost[i] << endl;
		
	multiset<vector<int>> st;
	for(int i = 0; i < S.size(); i+=2) {
		for(int j = i+1; j <= S.size(); j+=2) {
			vector <int > v = vector<int>(S.begin() + i, S.begin() + j);
			//for(int i: v) cout << i << " ";
			//cout << endl;
			st.insert(vector<int>(S.begin() + i, S.begin() + j));
		}
	}
	//cout << endl;
	
	int result = 0;
	for(int i = 2; i < N; i+=2) {
		int c = 0;
		int j = i+1;
		for(; j <= S.size(); j+=2) {
			vector<int> v = vector<int>(S.begin()+i, S.begin() + j);
			//for(int a: v) cout << a << " ";
			if(st.count(vector<int>(S.begin()+i, S.begin() + j)) == 1) break;
			c += S[j];
			//cout << c << endl;
		}
		result = max(result, c + cost[j/2] - cost[i/2]);
	}
	cout << result << endl;
}
