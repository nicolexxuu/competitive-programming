// Landscaping

import java.util.*;
import java.io.*;

public class landscape {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int Z = Integer.parseInt(st.nextToken());
		
		
		
		ArrayList<Integer> a = new ArrayList<>();
		ArrayList<Integer> b = new ArrayList<>();
		a.add(0); b.add(0);
		
		int ac = 0, bc = 0;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			a.add(Integer.parseInt(st.nextToken()));
			ac += a.get(i);
			b.add(Integer.parseInt(st.nextToken()));
			bc += b.get(i);
		}
		br.close();
		int[] A = new int[ac + 1];
		int[] B = new int[bc + 1];
		
		
		int ind = 1;
		for(int i = 1; i < a.size(); i++) {
			int j = a.get(i);
			for(int k = 0; k < j; k++) {
				A[ind] = i;
				ind++;
			}
		}
		ind = 1;
		for(int i = 1; i < b.size(); i++) {
			int j = b.get(i);
			for(int k = 0; k < j; k++) {
				B[ind] = i;
				ind++;
			}
		}
		
		int[][] dp = new int[ac+1][bc+1];
		for(int i = 0; i <= ac; i++) dp[i][0] = Y * i;
		for(int i = 0; i <= bc; i++) dp[0][i] = X * i;
		

		for(int i = 1; i <= ac; i++) {
			for(int j = 1; j <= bc; j++) {
				dp[i][j] = dp[i-1][j-1] + Z * Math.abs(A[i] - B[j]);
				dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + Y);
				dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + X);
			}
		}

		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(dp[ac][bc]);
		//out.println(result);
		//out.close();
	}
}
