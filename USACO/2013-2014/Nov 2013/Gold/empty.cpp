#include <iostream>
#include <algorithm>

using namespace std;

int cows[3000000];

int main() {
	int N, K;
	cin >> N >> K;
	
	for(int i = 0; i < K; i++) {
		int X, Y, A, B;
		cin >> X >> Y >> A >> B;
		for(int j = 1; j <= Y; j++) {
			int ind = (int)(((long)A*j + B) % N);
			cows[ind] += X;
		}
	}
	
	int res = -1, curr = 0;


	for(int n = 0; n < 2; n++) {
		for(int i = 0; i < N; i++) {
			if(cows[i] == 0) {
				if(curr == 0) {
					if(n) res = min(res, i);
					else res = i;
				} else {
					cows[i]++;
					curr--;
				}
			} else {
				curr += cows[i] - 1;
				cows[i] = 1;
			}
	
		}
	}

	
	cout << res << endl;
}
