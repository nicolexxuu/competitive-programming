// "City Horizon"

import java.util.*;
import java.io.*;

public class horizon {
	public static void main(String[] args) throws IOException {
		String file = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] locs = new int[N*2][3];
		
		for(int i = 0; i < N*2; i+=2) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			locs[i][0] = A;
			locs[i][1] = H;
			locs[i][2] = 1;
			locs[i+1][0] = B;
			locs[i+1][1] = H;
			locs[i+1][2] = 0;
		}
		
		PriorityQueue<Integer> current = new PriorityQueue<>((x, y) -> y - x);
		Arrays.sort(locs, (x, y) -> x[0] - y[0]);
		br.close();

		long result = 0;
		
		for(int loc = 0; loc < N*2; loc++) {
			//System.out.println("curr: " + locs[loc][0]);
			if(!current.isEmpty()) {
				//System.out.println((locs[loc][0] + " and " + locs[loc-1][0]));
				//System.out.println((locs[loc][0] - locs[loc-1][0]) * current.peek());
				result += (locs[loc][0] - locs[loc-1][0]) * (long)current.peek();
			}
			
			if(locs[loc][2] == 1) { 
				//System.out.println("adding");
				current.add(locs[loc][1]);
			} else {
				current.remove(locs[loc][1]);
			}
		}
		
		System.out.println(result);
	}
}
