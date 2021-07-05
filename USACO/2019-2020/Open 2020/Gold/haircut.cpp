// Haircut

#include <iostream>
#include <fstream>
#include <algorithm>
#define MAXN 100005

using namespace std;

int BIT[MAXN];
long long pairs[MAXN];
void upd(int idx) {
	while(idx < MAXN) {
		BIT[idx] += 1;
		idx += idx & -idx;
	}
}

int get(int idx) {
	int sum = 0;
	while(idx > 0) {
		sum += BIT[idx];
		idx -= idx & -idx;
	}
	
	return sum;
}


int main() {
	int N; 
	ifstream cin("haircut.in");
	ofstream cout("haircut.out");
	cin >> N;
	
	for(int i = 0; i < N; i++) {
		int a;
		cin >> a;
		a++;
		pairs[a] += i - get(a); // if j is the max value: add all inversions where b < j
		upd(a);
	}
	
	long long total = 0;
	for(int i = 0; i < N; i++) {
		total += pairs[i];
		cout << total << endl;
	}
}
