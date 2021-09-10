#include <bits/stdc++.h>

using namespace std;

int main() {
	ifstream cin("square.in");
	ofstream cout("square.out");
	
	//read input
	int x1, y1, x2, y2;
	cin >> x1; //lower-left vertex of rectangle 1
	cin >> y1; //upper-right vertex of rectangle 1
	cin >> x2; //lower-left vertex of rectangle 2
	cin >> y2; //upper-right vertex of rectangle 2
	
	int x3, y3, x4, y4;
	cin >> x3; //lower-left vertex of rectangle 3
	cin >> y3; //upper-right vertex of rectangle 3
	cin >> x4; //lower-left vertex of rectangle 4
	cin >> y4; //upper-right vertex of rectangle 4
	
	// minimum and maximum x 
	int minX = min(x1, x3); //x1 < x2 and x3 < x4
	int maxX = max(x2, x4);
	
	// side length on horizontal direction
	int deltaX = maxX - minX;
	
	int minY = min(y1, y3); //y1 < y2 and y3 < y4
	int maxY = max(y2, y4);
	
	// side length on vertical direction
	int deltaY = maxY - minY;
	
	// side length of the smallest square
	int side = max(deltaX, deltaY);
	
	// write output
	cout << side * side << endl;
}
