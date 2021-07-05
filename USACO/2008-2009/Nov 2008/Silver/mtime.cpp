#include <iostream>
#include <algorithm>

using namespace std;

struct job {
	int t, s;
	
	bool operator <(job const&other) const {
		return other.s < s;
	}
}jobs[1000];

int main () {
	int N, latestTime = 1000000;
	
	cin >> N;
	for(int i = 0; i < N; i++) cin >> jobs[i].t >> jobs[i].s;
	
	sort(jobs, jobs+N);
	
	for(int i = 0; i < N; i++) latestTime = min(latestTime, jobs[i].s) - jobs[i].t;

	if(latestTime < 0) cout << -1 << endl;
	else cout << latestTime << endl;
}
