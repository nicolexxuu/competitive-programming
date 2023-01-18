// Grass Planting

import java.util.*;
import java.io.*;

public class planting {
	public static void main(String[] args) throws IOException {
		String file = "planting";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] d = new int[N+1];
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			d[a]++; d[b]++;
		}
		br.close();
		
		int res = 1;
		for(int i : d) res = Math.max(res, i+1);
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(res);
		out.println(res);
		out.close();
	}
}