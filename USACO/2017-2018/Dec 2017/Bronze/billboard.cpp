#include <bits/stdc++.h>

using namespace std;

struct Rect {int x1, y1, x2, y2;};

int area(Rect a) { return (a.x2 - a.x1) * (a.y2 - a.y1); }
int overlap(Rect a, Rect b) { return max(0, min(a.x2, b.x2) - max(a.x1, b.x1)) * max(0, min(a.y2, b.y2) - max(a.y1, b.y1)); }

int main() {
	ifstream cin("billboard.in");
	ofstream cout("billboard.out");
	Rect r1, r2, t;
	cin >> r1.x1 >> r1.y1 >> r1.x2 >> r1.y2;
	cin >> r2.x1 >> r2.y1 >> r2.x2 >> r2.y2;
	cin >> t.x1 >> t.y1 >> t.x2 >> t.y2;

	cout << area(r1) + area(r2) - overlap(r1, t) - overlap(r2, t) << endl;
}
