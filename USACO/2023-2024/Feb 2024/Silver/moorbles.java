import java.util.*;
import java.io.*;

public class moorbles {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
			
			int[][] delta = new int[M][2];
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				Arrays.fill(delta[i], Integer.MAX_VALUE);
				for(int k = 0; k < K; k++) {
					int a = Integer.parseInt(st.nextToken());
					delta[i][1-a%2] = Math.min(delta[i][1-a%2], -a);
					delta[i][a%2] = Math.min(delta[i][a%2], a);
				}
			}
			
			long[] minmar = new long[M+1];
			minmar[M] = 1; 
			for(int i = M-1; i >= 0; i--) 
				minmar[i] = Math.max(minmar[i+1] - Math.max(delta[i][0], delta[i][1]), 1);
			
			if(N < minmar[0]) System.out.println(-1);
			else {
				for(int i = 0; i < M; i++) {
					int choice = N + delta[i][0] >= minmar[i+1] ? 0 : 1;
					N += delta[i][choice];
					System.out.print((choice == 0 ? "Even" : "Odd"));
					if(i < M-1) System.out.print(" ");
				}
				System.out.println();
			}
		}
		
		br.close();
	}
}