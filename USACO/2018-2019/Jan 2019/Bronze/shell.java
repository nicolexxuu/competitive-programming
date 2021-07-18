// Shell Game

import java.util.*;
import java.io.*;

public class shell {
	public static void main(String[] args) throws IOException {
		String file = "shell";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] swaps = new int[N][3];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			swaps[i][0] = Integer.parseInt(st.nextToken());
			swaps[i][1] = Integer.parseInt(st.nextToken());
			swaps[i][2] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(Math.max(simulate(swaps, N, 1), Math.max(simulate(swaps, N, 2), simulate(swaps, N, 3))));
		out.println(Math.max(simulate(swaps, N, 1), Math.max(simulate(swaps, N, 2), simulate(swaps, N, 3))));
		out.close();
	}
	
	public static int simulate(int[][] swaps, int N, int shell) {
		int count = 0;
		for(int swap = 0; swap < N; swap++) {
			if(shell == swaps[swap][0]) shell = swaps[swap][1];
			else if(shell == swaps[swap][1]) shell = swaps[swap][0];
			if(shell == swaps[swap][2]) count++;
		}
		return count;
	}
}