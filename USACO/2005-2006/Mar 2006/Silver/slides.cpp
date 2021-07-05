#include <iostream>
#include <vector>
#include <algorithm>
#define MAXN 10000

using namespace std;

int N, M;
int pos[MAXN], inDegrees[MAXN], outDegrees[MAXN];

int main() {
	cin >> N >> M;
	for(int i = 0; i < N; i++) {
		cin >> pos[i];
	}
	
	for(int i = 0; i < M; i++) {
		int a, b;
		cin >> a >> b;
		a--; b--;
		inDegrees[b]++;
		outDegrees[a]++;
	}
	
	vector <int> out;
	vector <int> in;
	
	for(int i = 0; i < N; i++) {
		if(inDegrees[i] == outDegrees[i]) continue;
		if(inDegrees[i] > outDegrees[i]) {
			for(int j = 0; j < inDegrees[i] - outDegrees[i]; j++) out.push_back(pos[i]);
		} else {
			for(int j = 0; j < outDegrees[i] - inDegrees[i]; j++) in.push_back(pos[i]);
		}
	}
	
	sort(in.begin(), in.end());
	sort(out.begin(), out.end());
	
	int result = 0;
	for(int i = 0; i < out.size(); i++) result += abs(out[i] - in[i]);
	cout << result << '\n';
}
