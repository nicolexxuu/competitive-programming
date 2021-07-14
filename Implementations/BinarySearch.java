// Binary Search Implementations
// O(log N)

import java.util.*;
import java.io.*;

public class BinarySearch {
	
	public static int lowerBound(int[] A, int target) {
//		if (A[0] >= target)
//			return 0;
//		if (A[A.length - 1] < target)
//			return -1;
		int lo = 0;
		int hi = A.length - 1;
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (A[mid] >= target) hi = mid;
			else lo = mid + 1;
		}

		return lo;
	}

	public static int upperBound(int[] A, int target) {
		int lo = 0;
		int hi = A.length - 1;
		while (lo < hi) {
			int mid = (lo + hi + 1) / 2;
			if (A[mid] <= target) lo = mid;
			else hi = mid - 1;
		}

		return hi;
	}
}
