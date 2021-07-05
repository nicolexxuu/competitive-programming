// Why Did the Cow Cross the Road III

import java.util.*;
import java.io.*;

public class circlecross {
	static int[] BIT;
	public static void main(String[] args) throws IOException {
		String file = "circlecross";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] pts = new int[N][2];
		int[] circle = new int[N*2];
		for(int[] a: pts) Arrays.fill(a, -1);
		
		for(int i = 0; i < N*2; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken()) - 1;
			circle[i] = id;
			if(pts[id][0] == -1) pts[id][0] = i;
			else pts[id][1] = i;
		}
		br.close();
		
		int result = 0;
		BIT = new int[N*2 + 1];
		
		for(int pt = 0; pt < N*2; pt++) {
			int start = pts[circle[pt]][0];
			if(start == pt) { // start
				add(start, true);
			} else { // end
				add(start, false);
				result += sum(pt-1) - sum(start);
			}
		}

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		out.println(result);
		out.close();
	}

	public static int sum(int m) {
		m++;
		int sum = 0;
		while (m > 0) {
			sum += BIT[m];
			m -= m & (-m); 
		}
		return sum;
	}

	public static void add(int m, boolean add) {
		m++;
		while (m <= BIT.length) {
			if(add) BIT[m]++;
			else BIT[m]--;
			m += m & (-m);
		}
	}
}