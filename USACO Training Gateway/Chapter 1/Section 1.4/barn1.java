/*
ID: diudiux1
LANG: JAVA
TASK: barn1
 */

import java.util.*;
import java.io.*;

public class barn1 {
	public static void main(String[] args) throws IOException {
		String file = "barn1";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] occupied = new int[C];
		ArrayList<Integer> gaps = new ArrayList<>();
		for(int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			occupied[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(occupied);
		for(int i = 1; i < C; i++) {
			gaps.add(occupied[i] - occupied[i-1] - 1);
		}
		br.close();
		
		Collections.sort(gaps, (a, b) -> b - a);
		int blocked = occupied[C-1] - occupied[0] + 1;
		for(int i = 0; i < Math.min(M-1, C-1); i++) {
			blocked -= gaps.get(i);
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(blocked);
		out.println(blocked);
		out.close();
	}
}