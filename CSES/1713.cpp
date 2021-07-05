#include <bits/stdc++.h>

using namespace std;

int prime_div[1000001];

int main() {
	// Sieve of Eratosthenes
	for(int i = 2; i <= 1000000; i++) {
		if(!prime_div[i]) {
			for(int j = i; j <= 1000000; j += i) {
				prime_div[j] = i;
			}			
		}	
	}
	
	
	int n; 
	cin >> n;
	for(int i = 0; i < n; i++) {
		int x;
		cin >> x;
		
		int res = 1;
		while(x != 1) {
			int prime = prime_div[x], count =  0;
			while(x % prime == 0) {
				count++;
				x /= prime;
			}
			
			res *= count + 1;
		}
		
		cout << res << endl;
	}
}
