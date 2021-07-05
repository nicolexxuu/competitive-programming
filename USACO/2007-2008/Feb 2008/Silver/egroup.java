// Eating Together

import java.util.*;
import java.io.*;

public class egroup {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[] cows = new int[N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = Integer.parseInt(st.nextToken());
		}
		br.close();

		int[][] asc = new int[N+1][5];
		int[][] des = new int[N+1][5];
		
		for(int cow = 1; cow <= N; cow++) {
			int val = cows[cow];
			int add = 0;
			
			for(int up = 1; up <= 3; up++) {
				if(val != up) add = 1;
				int min = Integer.MAX_VALUE;
				for(int a = up; a >= 1; a--) min = Math.min(min, asc[cow-1][a]);
				asc[cow][up] = min + add;
			}
			
			for(int down = 3; down >= 1; down--) {
				if(val != down) add = 1; else add = 0;
				int min = Integer.MAX_VALUE;
				for(int a = down; a <= 3; a++) min = Math.min(min, des[cow-1][a]);
				des[cow][down] = min + add;
			}
		}

		
		int result = Integer.MAX_VALUE;
		for(int i = 1; i <= 3; i++) {
			result = Math.min(result, asc[N][i]);
			result = Math.min(result, des[N][i]);
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
