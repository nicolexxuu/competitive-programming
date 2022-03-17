#include <bits/stdc++.h>
#define pii pair<int, int>

using namespace std;
char l[1000][1000];
bool mark[1000][1000];
int par[1000][1000];
int dr[]{-1, 0, 1, 0}, dc[]{0, 1, 0, -1};

int main() {
	int n, m;
	cin >> n >> m;
	
	int sr = 0, sc = 0, er = 0, ec = 0;
	for(int i = 0; i < n; i++) {
		for(int j = 0; j < m; j++) {
			cin >> l[i][j];
			if(l[i][j] == 'A') {
				sr = i;
				sc = j;
			}
			if(l[i][j] == 'B') {
				er = i;
				ec = j;
			}
		}
	}
	
	deque<pii> toVisit;
	mark[sr][sc] = true;
	par[sr][sc] = -1;
	toVisit.push_back({sr, sc});
	
	while(toVisit.size()) {
		pii curr = toVisit.front();
		toVisit.pop_front();
		int r = curr.first, c = curr.second;
		
		for(int k = 0; k < 4; k++) {
			int nr = r + dr[k], nc = c + dc[k];
			if(nr < 0 || nr >= n || nc < 0 || nc >= m || mark[nr][nc] || l[nr][nc] == '#') continue;
			mark[nr][nc] = true;
			par[nr][nc] = k;
			toVisit.push_back({nr, nc});
		}
	}
	
	if(mark[er][ec]) {
		cout << "YES" << '\n';
		string path = "";
		int cr = er, cc = ec;
		while(par[cr][cc] != -1) {
			int k = par[cr][cc];
			path += ((k == 0) ? 'U' : (k == 1) ? 'R' : (k == 2) ? 'D' : 'L');
			cr += dr[(k+2)%4], cc += dc[(k+2)%4];
		}
		
		cout << path.size() << '\n';
		for(int i = path.size()-1; i >= 0; i--) cout << path[i];
	} else {
		cout << "NO" << '\n';
	}
}
