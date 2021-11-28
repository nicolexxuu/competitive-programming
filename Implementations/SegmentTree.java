// Segment Tree (Range Sum) Implementation

import java.util.*;
import java.io.*;

public class SegmentTree {
	static int[] arr = new int[200000];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("test_input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		SegTree segtree = new SegTree(n);
		
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(t == 2) {
				System.out.println(segtree.query(a-1, b));
			} else {
				segtree.update(a-1, b - arr[a-1]);
				arr[a-1] = b;
			}
		}
		br.close();
		
		
	}
	
	public static class SegTree {
		int sz;
		long[] tree;
		
		// build and initialize tree
		// O(N)
		SegTree(int N) {
			sz = N;
			tree = new long[sz*2];
			
			for(int i = 0; i < N; i++) tree[sz+i] = arr[i]; // original values
			for(int i = sz - 1; i > 0; i--) tree[i] = tree[i * 2] + tree[i * 2 + 1]; // calculate parent node
		}
		
		// add value x to element at position idx
		// O(log N)
		void update(int idx, long x) {
			idx += sz;
			tree[idx] += x; // add value at idx
			for(int i = idx / 2; i > 0; i /= 2) tree[i] = tree[i * 2] + tree[i * 2 + 1]; // update parents
		}
		
		// get sum of element values within range [lo, hi)
		// O (log N)
		long query(int lo, int hi) {
			lo += sz;
			hi += sz - 1;
			long total = 0;
			
			while(lo <= hi) {
				if(lo % 2 == 1) // lo is a right child -> parent not included
					total += tree[lo++]; // move lo to the right
				if(hi % 2 == 0) // hi is a left child -> parent not included
					total += tree[hi--]; // move hi to the left
				
				// go to parents; move up the tree
				lo /= 2; 
				hi /= 2;
			}
			
			return total;
		}
	}
}
