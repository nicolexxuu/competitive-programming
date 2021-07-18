// Diamond Collector

import java.util.*;
import java.io.*;

public class diamond {
	public static void main(String[] args) throws IOException {
		String file = "diamond";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] diamonds = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			diamonds[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		Arrays.sort(diamonds);
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = i; j < N; j++) {
				if(diamonds[j] - diamonds[i] <= K) max = Math.max(max, j-i+1);
			}
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(max);
		out.println(max);
		out.close();
	}
}