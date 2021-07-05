#include <iostream>
#include <map>
#include <algorithm>

using namespace std;

const int MAXN = 20000;
int N;
map<int, int> pos[5];
int A[MAXN];

bool cmp(int a, int b) {
	int cnt = 0;
	for(int i = 0; i < 5; i++) {
		cnt += pos[i][a] < pos[i][b];
	}
	return cnt > 2;
}

int main () {
	cin >> N;
	
	for(int i = 0; i < 5; i++) {
		for(int j = 0; j < N; j++) {
			int a; cin >> a;
			A[j] = a;
			pos[i][a] = j;
		}
	}
	
	sort(A, A + N, cmp);
	for(int i = 0; i < N; i++) cout << A[i] << endl;
}
