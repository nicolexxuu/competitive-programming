// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

//http://www.usaco.org/index.php?page=viewproblem2&cpid=767
//"Haybale Feast", 2017 December Gold Contest

import java.util.*;
import java.io.*;

public class hayfeast {
	public static void main(String[] args) throws IOException {
		String file = "hayfeast";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		
		int[] flavor = new int[N];
		int[] spicy = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			flavor[i] = Integer.parseInt(st.nextToken());
			spicy[i] = Integer.parseInt(st.nextToken());
		}
		
		SegTreeMax tree = new SegTreeMax(N);
		tree.buildTree(spicy);
		
		br.close();

		int result = Integer.MAX_VALUE;
		int l = 0; //leftmost element of curr range
		int r = 0; //rightmost element of curr range
		long currFlavor = flavor[0];
		while(l < N && r < N) {
			
			if(currFlavor >= M) {
				result = Math.min(result, tree.getMax(l, r));
			}
			
			if(l == r || currFlavor <= M) {
				r++;
				if(r < N) currFlavor += flavor[r];
			} else {
				currFlavor -= flavor[l];
				l++;
			}
		}
		
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	public static class SegTreeMax {
		//accept an integer array "A"
		//build another array tree representing the segment tree
		
		int[] tree;   // tree array
		int[] A;   // original array of integers

		int height;   // height of the segment tree
		int maxSize;  // the size of the segment tree in its entirety
		              //   (some nodes may have unused zeros)
		
		int STARTINDEX = 0;   // the left most range of any query
		int ENDINDEX;         // the right most range of any query
		
		SegTreeMax(int n) {
			A = new int[n];
			Arrays.fill(A, Integer.MIN_VALUE);

			height = (int) Math.ceil( Math.log(n)/Math.log(2) );
			maxSize = (int) Math.pow(2, height+1) - 1; // 1 << height+1 - 1;
			tree = new int[maxSize];
			ENDINDEX = n - 1;
		}
		
		public int leftChild(int k) {return 2*k+1;}
		public int rightChild(int k) {return 2*k+2;}
		
		// build the whole segment tree [ O(n) ]
		public void buildTree(int[] elements) {

			for(int k=0; k<A.length; k++) {
				A[k] = elements[k];
			}
			
			buildTree(STARTINDEX, ENDINDEX, 0);
		}

		// build the subtree (where root1 is the root) which represents [start, end]
		private int buildTree(int low, int high, int currNode) {
			if( low==high ) {
				tree[currNode] = A[low];
			} else {
				int mid = (low + high)/2;
				tree[currNode] = Math.max( buildTree(low, mid, leftChild(currNode))
		        		, buildTree(mid + 1, high, rightChild(currNode)) );
			}
			
	        return tree[currNode];
		}
		
		// find the max of A[x...y], inclusive
		public int getMax(int x, int y) {
			if(x < 0 || y >= A.length)
				return Integer.MIN_VALUE;
			
			return getMax(STARTINDEX, ENDINDEX, x, y, 0);
		}
				
		// find the max from x to y in the node
		// [start, end]: range of current node
		// [x, y]: query range
		// currNode: ID of current node
		private int getMax(int start, int end, int x, int y, int currNode) {
			
			if( y < start || end < x ) // [x, y] contains [startIndex, endIndex]
				return Integer.MIN_VALUE;

			if( x <= start && end <= y ) // [x, y] doesn't overlap [startIndex, endIndex]
				return tree[currNode];

			// there is overlap between [start, end] and [low, high]
			int mid = (start+end)/2;
			int s1 = getMax(start, mid, x, y, leftChild(currNode));
			int s2 = getMax(mid + 1, end, x, y, rightChild(currNode));
			return Math.max(s1, s2);
		}
		
		// set value at position "loc" to "val": A[loc] = val
		// (find the difference and update for all parent nodes!)
		public void setValue(int loc, int val) {
			int diff = val - A[loc];
			setValue(STARTINDEX, ENDINDEX, loc, diff, 0);
		}
		
		// [start, end]: range of current node
		// loc: position to be modified
		// diff: the change to the specified position
		private void setValue(int start, int end, int loc, int diff, int currNode) {
			if( start==end ) {  // leaf node
				A[loc] += diff;
				tree[currNode] += diff;
			} else {
				int mid = (start+end)/2;
				
				if( start<=loc && loc<=mid ) { // loc is in the left child
					setValue(start, mid, loc, diff, leftChild(currNode));
				} else { // loc is in the right child
					setValue(mid+1, end, loc, diff, rightChild(currNode));
				}
				tree[currNode] = Math.max(tree[leftChild(currNode)], tree[rightChild(currNode)]);
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


