#include <cstdio>
#include <iostream>
#include <cmath>
#include <utility>
#include <queue>

int N, W;
double M;
int x[1000], y[1000];
double dist[1000][1000];
double res[1000];
bool mark[1000];

using namespace std;

typedef pair<int, double> pi;
			//node, dist
struct comp {
	bool operator() (pair<int, double> a, pair<int, double> b) {
		return a.second > b.second;
	}
};

int main () {
		freopen ("pwrfail_data/pwrfail.9.in", "r", stdin);
		
		scanf("%i %i", &N, &W);
		scanf("%lf", &M);
		
		for(int i = 0; i < N; i++) scanf("%i %i", &x[i], &y[i]);
		cout << N << " " << W << " " << M << endl;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				dist[i][j] = hypot(x[i] - x[j], y[i] - y[j]);
	
		for(int i = 0; i < W; i++) {
			int a, b;
			scanf("%i %i", &a, &b);
			cout << a << " " << b << endl;
			a--; b--;
			dist[a][b] = 0;
			dist[b][a] = 0;
		}
		

		for(int i = 0; i < N; i++) 	res[i] = -1;
		cout << "sadkjflkdjsaaaaaaadfsssssssssssssssssss" << endl;
		res[0] = 0;
		priority_queue<pi, vector<pi>, comp> pq;
		pq.push(make_pair(0, 0));
		while(!pq.empty()) {
			pair<int, double> curr = pq.top();
			int x = curr.first;
			cout << x << endl;
			double y = curr.second;
			
			
			pq.pop();
			
			if(mark[x]) continue;
			mark[x] = true;
			
			for(int to = 0; to < N; to++) {
				if(!mark[to] && dist[x][to] <= M && (res[to] == -1 || y + dist[x][to] < res[to])) {
					res[to] = y + dist[x][to];
					pq.push(make_pair(to, y + dist[x][to]));
				}
			}
		}
		
		if(res[N-1] == -1) cout << -1 << '\n';
		else cout << (int)(res[N-1]*1000) << '\n';
}
