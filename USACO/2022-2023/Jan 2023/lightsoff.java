import java.util.*;
import java.io.*;

public class lightsoff {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken());
		int[][] shift = new int[N+1][N];
		for(int m = 1; m <= N; m++) {
			int msk = (1 << m) - 1;
			shift[m][0] = msk;
			
			for(int i = 1; i < N; i++) {
				msk = (msk >> 1) + ((msk & 1) << (N-1));
				shift[m][i] = msk;
			}
		}
		
		boolean[][] dp = new boolean[N+1][1<<N];
		dp[0][0] = true;
		for(int m = 1; m <= N; m++) {
			for(int mask = 0; mask < (1 << N); mask++) {
				for(int i = 0; i < N; i++) {
					dp[m][mask] |= dp[m-1][mask ^ shift[m][i]];
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int lt = Integer.parseInt(st.nextToken(), 2), sw = Integer.parseInt(st.nextToken(), 2);
			int curr = lt;
			for(int m = 0; m <= N; m++) {
				if(dp[m][curr]) {
					sb.append(m).append('\n');
					break;
				} 
				
				curr ^= sw;
				sw = (sw >> 1) + ((sw & 1) << (N-1));
			}
		}
		System.out.print(sb);
		br.close();
		
		
	}
}