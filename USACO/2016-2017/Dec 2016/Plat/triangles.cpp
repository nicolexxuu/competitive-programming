#include <iostream>
#include <algorithm>
#include <vector>
#define f first
#define s second
#define ll long long

using namespace std;

typedef pair<int, int> pii;

int N;
vector<pii> pts;
int res[300], under[300][300];

bool operator<(pii a, pii b) {
	if(a.f < b.f) return true;
	if(a.f > b.f) return false;
	return a.s < b.s;
}

ll cross(pii a, pii b) {
	return (ll)a.f*b.s - (ll)a.s*b.f;
}

int main() {
	cin >> N;
	
	for(int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;
		pts.push_back(make_pair(x, y));
	}
	
	sort(pts.begin(), pts.end());
	
	for(int a = 0; a < N; a++) {
		for(int b = a+1; b < N; b++) {
			//cout << pts[a].f << " " << pts[a].s << " " << pts[b].f << " " << pts[b].s << endl;
			for(int c = a + 1; c < b; c++) {
//				if(cross(make_pair(pts[c].f - pts[a].f, pts[c].s - pts[a].s), make_pair(pts[b].f - pts[a].f, pts[b].s - pts[a].s)) > 0) {
//					under[a][b]++;
//				}

				if(cross(make_pair(pts[b].f - pts[c].f, pts[b].s - pts[c].s), make_pair(pts[a].f - pts[c].f, pts[a].s - pts[c].s)) > 0) under[a][b]++;

//				if(cross(make_pair(pts[a].f - pts[c].f, pts[a].s - pts[c].s), make_pair(pts[b].f - pts[a].f, pts[b].s - pts[a].s)) < 0) {
//					under[a][b]++;
//				}
			}
			
		}
	}

	for(int p1 = 0; p1 < N; p1++) {
		for(int p2 = p1 + 1; p2 < N; p2++) {
			for(int p3 = p2 + 1; p3 < N; p3++) {
				int a;
				if(pts[p1].f == pts[p2].f) { // case 3
					a = under[p2][p3] - under[p1][p3];
				} else if (pts[p2].f == pts[p3].f) { // case 4
					a = under[p1][p3] - under[p1][p2] - 1;
				} else if (cross(make_pair(pts[p2].f - pts[p1].f, pts[p2].s - pts[p1].s), make_pair(pts[p3].f - pts[p2].f, pts[p3].s - pts[p2].s)) < 0) { // case 1
					a = under[p1][p2] + under[p2][p3] - under[p1][p3];
				} else { // case 2
					a = under[p1][p3] - under[p1][p2] - under[p2][p3] - 1;
				}
				
				res[a]++;

			}
		}
	}

	
	for(int i = 0; i < N-2; i++) cout << res[i] << endl;
}
