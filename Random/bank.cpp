// Bank - IZhO 2014

#include <bits/stdc++.h>
#define MAXN 21

using namespace std;
int N, M;
int a[MAXN], b[MAXN];
int num_people[1 << MAXN], leftover[1 << MAXN];

int main() {
	cin >> N >> M;
	for(int i = 0; i < N; i++) cin >> a[i];
	for(int i = 0; i < M; i++) cin >> b[i];
	
	bool ok = false;
	for(int mask = 0; mask < (1 << M); mask++) {
		int p = num_people[mask];
		ok |= (p == N);
		if(mask > 0 && (p == 0 && leftover[mask] == 0)) continue;
		for(int note = 0; note < M; note++) {
			if((mask & (1 << note)) == 0) {
				int new_mask = mask + (1 << note);
				if(leftover[mask] + b[note] < a[p]) {
					num_people[new_mask] = p;
					leftover[new_mask] = leftover[mask] + b[note];
				} else if(leftover[mask] + b[note] == a[p]) {
					num_people[new_mask] = p+1;
					leftover[new_mask] = 0;
				}
			}
		}
	}
	
	if(ok) cout << "YES" << endl;
	else cout << "NO" << endl;
}

// alternate solution ..
//#include <bits/stdc++.h>
//#define MAXN 21
//using namespace std;
//
//int a[MAXN], b[MAXN];
//bool possible[MAXN][1 << MAXN];
//vector<int> combos[2005];
//int N, M;
//
//int main() {
//	ios_base::sync_with_stdio(0); 
//	cin.tie(NULL);
//	cin >> N >> M;
//	for(int i = 0; i < N; i++) cin >> a[i];
//	for(int i = 0; i < M; i++) cin >> b[i];
//	
//	for(int mask = 0; mask < (1 << M); mask++) {
//		int sum = 0;
//		for(int i = 0; i < M; i++)
//			if(mask & (1 << i)) sum += b[i];
//		if(sum <= 1000) combos[sum].push_back(mask);
//	}
//	
//	possible[0][0] = true;
//	for(int i = 0; i < N; i++) {
//		for(int mask = 0; mask < (1 << M); mask++) {
//			if(!possible[i][mask]) continue; // how much does this reduce complexity by?? lol
//			for(int combo : combos[a[i]])
//				if((combo & mask) == 0) possible[i+1][combo + mask] |= possible[i][mask];
//		}
//	}
//	
//	bool res = false;
//	for(int i = 0; i < (1 << M); i++) res |= possible[N][i];
//	if(res) cout << "YES\n";
//	else cout << "NO\n";
//}
