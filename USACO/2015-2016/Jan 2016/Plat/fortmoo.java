// Fort Moo

import java.util.*;
import java.io.*;

public class fortmoo {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		boolean[][] grid = new boolean[M+1][N+1];
		int[][] ps = new int[M+1][N+1];
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for(int j = 1; j <= N; j++) {
				grid[i][j] = s.charAt(j-1) == 'X';
				ps[i][j] = ps[i-1][j];
				if(grid[i][j]) ps[i][j]++;
			}
		}
		br.close();
		

		int result = 0;
		for(int b = 1; b <= M; b++) {
			for(int t = 1; t < b; t++) {
				int l = -1, r = -1;
				for(int i = 1; i <= N; i++) {
					if(ps[b][i] - ps[t-1][i]== 0) {
						if(l == -1) l = i;
						else r = i;
					} else if(grid[t][i] || grid[b][i]) {
							l = -1; r = -1;
					}
					
					if(r != -1 && l != -1) result = Math.max(result, (b-t+1)*(r-l+1));
				}
			}
		}
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
}
