#include <bits/stdc++.h>

using namespace std;
long long chg[500000];

int main() {
	int N; cin >> N;
	long long sum = 0;
	vector<int> a(N);
	for(int i = 0; i < N; i++) {
		cin >> a[i];
		sum += a[i];
	}
	rotate(a.begin(), next(min_element(a.begin(), a.end())), a.end());
	
	deque<int> d;
	d.push_front(N-1);
	for(int j = N-2; j >= 0; j--) {
		while(a[j] < a[d.front()]) {
			if(d.size() > 1) {
				int t1 = d[0], t2 = d[1], cng = a[t1]-a[t2];
				chg[t2-t1] += cng;
				chg[t2-t1 + t1-j] -= cng;
			}
			d.pop_front();
		}
		d.push_front(j);
	}
	
	while(d.size() > 1) {
		int t1 = d[0], t2 = d[1], cng = a[t1]-a[t2];
		chg[t2-t1] += cng; // since a[t1] is min(a[0] ... a[t1]), milk is limited by cng only once
		chg[t2+1] -= cng;
		d.pop_front();
	}
	
	for(int i = 1; i <= N; i++) {
		chg[i] += chg[i-1];
		sum -= chg[i];
		cout << sum << endl;
	}
}
