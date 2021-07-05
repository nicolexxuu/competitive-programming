#include <iostream>
#include <algorithm>
#include <cstdio>

int N, B;

const int MAXN = 100000;
int depth[MAXN], boots[MAXN][2];
int did[MAXN], bid[MAXN];
int prv[MAXN], nxt[MAXN];
int res[MAXN];

using namespace std;

bool boots_comp(int a, int b) {
	return boots[b][0] < boots[a][0];
}

bool depth_comp(int a, int b) {
	return depth[b] < depth[a];
}

int main() {
	freopen("snowboots.in", "r", stdin);
	
	//cin >> N >> B;
	scanf("%d %d", &N, &B);
	for(int i = 0; i < N; i++) {
		scanf("%d", &depth[i]);
		did[i] = i;
		prv[i] = i-1;
		nxt[i] = i+1;
	}
	
	for(int i = 0; i < B; i++) {
		scanf("%d %d", &boots[i][0], &boots[i][1]);
		bid[i] = i;
	}
	
	sort(bid, bid+B, boots_comp);
	sort(did, did+N, depth_comp);
	
	int ind = 0;
	int maxDist = 1;
	for(int i = 0; i < B; i++) {
		int id = bid[i];
		int deep = boots[id][0];
		int step = boots[id][1];
		
		while(depth[did[ind]] > deep) {
			int d = did[ind];
			prv[nxt[d]] = prv[d];
			nxt[prv[d]] = nxt[d];
			maxDist = max(maxDist, nxt[d] - prv[d]);
			ind++;
		}
		
		if(maxDist > step) res[id] = 0;
		else res[id] = 1;
	}
	
	freopen("snowboots.out", "w", stdout);
	for(int i = 0; i < B; i++) cout << res[i] << endl;
}
