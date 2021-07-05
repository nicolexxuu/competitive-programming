// Backward Digit Sums

import java.util.*;
import java.io.*;

public class bds {
	static int N, S;
	static boolean found = false;
	static int[] multiples;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		br.close();
		
		multiples = pascal(N);
		printOrder(new ArrayList<Integer>(), 0, 0);
		
	}
	
	public static void printOrder(ArrayList<Integer> order, int i, int sum) {
		if(sum == S && order.size() == N) {
			for(int j = 0; j < order.size()-1; j++) {
				System.out.print(order.get(j) + " ");
			}
			System.out.println(order.get(order.size()-1));
			found = true;
		}
		if(found || sum == S || order.size() == N) return;
		for(int j = 1; j <= N; j++) {
			if(order.contains(j)) continue;
			order.add(j);
			printOrder(order, i+1, sum + (multiples[i]*j));
			order.remove(order.size()-1);
		}
	}
	public static int[] pascal(int N) {
		if(N == 1) return new int[] {1};
		int[] ret = new int[N];
		int[] above = pascal(N-1);
		ret[0] = 1;
		ret[ret.length-1] = 1;
		for(int i = 1; i < ret.length-1; i++) {
			ret[i] = above[i-1] + above[i];
		}
		return ret;
	}
}
