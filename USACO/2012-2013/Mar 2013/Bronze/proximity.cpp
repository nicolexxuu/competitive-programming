//Breed Proximity
#include <iostream>
#include <algorithm>

using namespace std;

struct Cow {
	int b, x;
	bool operator < (Cow const& other) const {
		if(b == other.b) return x > other.x;
		return b > other.b;
	}
};

int main () {
	int N, K;
	cin >> N >> K;
	Cow cows[N];
	for(int i = 0; i < N; i++) {
		cin >> cows[i].b;
		cows[i].x = i;
	}
	
	sort(cows, cows + N);
	
	for(int i = 0; i < N-1; i++) {
		if(cows[i].b == cows[i+1].b && cows[i].x - cows[i+1].x <= K) {
			cout << cows[i].b << '\n';
			return 0;
		}
	}
	
}
