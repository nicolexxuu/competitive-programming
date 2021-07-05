#include <iostream>
#include <algorithm>
#include <vector>
#define MAXN 100005

using namespace std;

struct Node {
	int mc, prof, pid, id;
};

bool operator <(Node a, Node b) {
	return a.prof > b.prof;
}


vector<Node> adj[MAXN], nodes;
int res[MAXN];
int N;
int pid = 1;
int BIT[MAXN];


void po(Node& n) {
	nodes[n.id].pid = pid;
	for(Node to: adj[n.id]) {
		pid++;
		po(to);
	}
	
	nodes[n.id].mc = pid;
}

int query(int a) {
	int sum = 0;
	while(a > 0) {
		sum += BIT[a];
		a -= a & -a;
	}
	
	return sum;
}

void update(int a) {
	while(a <= N) {
		BIT[a]++;
		a += a & -a;
	}
}

int main() {
	cin >> N;
	
	for(int i = 0; i < N; i++) {
		nodes.push_back(Node());
		nodes[i].id = i;
		cin >> nodes[i].prof;
	}

	for(int i = 1; i < N; i++) {
		int a;
		cin >> a;
		a--;
		adj[a].push_back(nodes[i]);
	}
	

	po(nodes[0]);
	sort(nodes.begin(), nodes.end());

	for(int i = 0; i < N; i++) {
		res[nodes[i].id] = query(nodes[i].mc) - query(nodes[i].pid);
		update(nodes[i].pid);
	}
	
	for(int i = 0; i < N; i++) cout << res[i] << endl;
}
