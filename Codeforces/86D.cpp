#include <bits/stdc++.h>

using namespace std;

const int BLOCK = 450;

struct query {
	int l, r, id;
	
	query(int _l, int _r, int _id) {
		l = _l;
		r = _r;
		id = _id;
	}
	
	bool operator <(const query& other) const {
		if(l/BLOCK == other.l/BLOCK) return r < other.r;
		return l/BLOCK < other.l/BLOCK;
	}
};

int A[200005];
long long res[200005];
int freq[1000001];

int main() {
	int n, t;
	cin >> n >> t;
	
	for(int i = 0; i < n; i++) {
		cin >> A[i];
	}
	
	vector<query> queries;
	for(int i = 0; i < t; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		queries.push_back(query(a, b, i));
	}
	
	sort(queries.begin(), queries.end());
	
	int l = 0, r = -1; // curr range [l, r]
	long long ans = 0;
	
	for(query q: queries) {
		while(r < q.r) { // adding to right
			r++;
			ans += (2*freq[A[r]]+1) * A[r];
			freq[A[r]]++;
		}
		
		while(r > q.r) { // removing from right
			ans -= (2*freq[A[r]]-1) * A[r];
			freq[A[r]]--;
			r--;
		}
		
		while(l < q.l) { // removing from left
			ans -= (2*freq[A[l]]-1) * A[l];
			freq[A[l]]--;
			l++;
		}
		
		while(l > q.l) { // adding to left
			l--;
			ans += (2*freq[A[l]]+1) * A[l];
			freq[A[l]]++;
		}
		
		res[q.id] = ans;
	}
	
	for(int i = 0; i < t; i++) cout << res[i] << endl;
}
