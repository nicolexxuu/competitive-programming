#include <bits/stdc++.h>

using namespace std;

int main() {
	int nums[7];
	for(int i = 0; i < 7; i++) cin >> nums[i];
	sort(nums, nums+7);
	
	int A = nums[0], B = nums[1], C = nums[6]-A-B;
	cout << A << " " << B << " " << C << endl;
}
