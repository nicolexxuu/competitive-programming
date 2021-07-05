// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class sleepy {
	public static void main(String[] args) throws IOException {
		String file = "sleepy";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] cows = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(st.nextToken())-1;
		}
		br.close();
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		int start = N-1;
		while(start>0 && cows[start]>=cows[start-1]) start--;
		out.println(start);
		
		int[] A =  new int[N];
		for(int i = start; i < N; i++) A[cows[i]] = 1;
		
		SegTree tree = new SegTree(N);
		tree.buildTree(A);
		
		
		
		for(int cow = 0; cow < start; cow++) {
			//System.out.println("cow: " + cow);
			out.print(start - cow -1 + tree.getSum(0, cows[cow]));
			tree.setValue(cows[cow], 1);
			if(cow != start-1) out.print(" ");
		}
		

		out.close();
	}
	
	public static class SegTree {
		int[] A;
		int[] tree;
		
		int height;
		int maxSize;
		
		int STARTINDEX = 0;
		int ENDINDEX;
		
		SegTree(int n) {
			A = new int[n];
			
			height = (int) Math.ceil(Math.log(n)/Math.log(2));
			maxSize = (1 << (height+1)) -1;
			tree = new int[maxSize];
			ENDINDEX = n-1;
		}
		
		public int leftChild(int k) {return 2*k+1;}
		public int rightChild(int k) {return 2*k+2;}
		
		public void buildTree(int[] elements) {
			for(int k = 0; k < A.length; k++) {
				A[k] = elements[k];
			}
			
			buildTree(STARTINDEX, ENDINDEX, 0);
		}
		
		public int buildTree(int low, int high, int currNode) {
			if(low==high) {
				tree[currNode] = A[low];
			} else {
				int mid = (low+high)/2;
				tree[currNode] = buildTree(low, mid, leftChild(currNode))
						+ buildTree(mid+1, high, rightChild(currNode));
			}

			return tree[currNode];
		}
		
		public int getSum(int x, int y) {
			if(x < 0 || y >= A.length) return Integer.MIN_VALUE;
			return getSum(STARTINDEX, ENDINDEX, x, y, 0);
		}
		
		public int getSum(int start, int end, int x, int y, int currNode) {
			if(x <= start && end <= y) return tree[currNode];
			if(x > end || y < start) return 0;
			
			int mid = (start + end)/2;
			return getSum(start, mid, x, y, leftChild(currNode)) +
					getSum(mid+1, end, x, y, rightChild(currNode));
		}
		
		public void setValue(int loc, int val) {
			int diff = val - A[loc];
			A[loc] = val;
			setValue(STARTINDEX, ENDINDEX, loc, diff, 0);
		}
		
		public void setValue(int start, int end, int loc, int diff, int currNode) {
			if(loc >= start && loc <= end) {
				tree[currNode] += diff;
				
				if(start != end) {
					int mid = (start + end)/2;
					setValue(start, mid, loc, diff, leftChild(currNode));
					setValue(mid+1, end, loc, diff, rightChild(currNode));
				}
			}
		}
		
		public void print() { //print all elements of tree
			for(int i = 0; i < tree.length; i++) {
				System.out.print(tree[i] + " " );
			}
			System.out.println();
		}
	}
}



