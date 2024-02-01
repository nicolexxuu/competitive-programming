import java.util.*;
import java.io.*;

public class findreplace {
	static long l, r;
	static int n;
	static long[][][] dp;
	static String[][] op;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Long.parseLong(st.nextToken())-1;
		r = Long.parseLong(st.nextToken())-1;
		n = Integer.parseInt(st.nextToken());
		op = new String[n][26];
		for(String[] a : op) Arrays.fill(a,  "");
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			op[i][st.nextToken().charAt(0) - 'a'] = st.nextToken();
		}
		br.close();
		
		dp = new long[n+1][26][3];
		// dp: num of leaves in subtree, earliest split, letter at split
		for(int a = 0; a < 26; a++) dp[n][a] = new long[] {1, n, a};
		
		for(int l = n-1; l >= 0; l--) {
			for(int a = 0; a < 26; a++) {
				if(op[l][a].length() == 1) {
					dp[l][a] = dp[l+1][op[l][a].charAt(0) - 'a'];
				} else if(op[l][a].length() > 0) {
					dp[l][a][1] = l; 
					dp[l][a][2] = a;
					for(int i = 0; i < op[l][a].length(); i++) {
						dp[l][a][0] += dp[l+1][op[l][a].charAt(i) - 'a'][0];
						if(dp[l][a][0] > r) break; // prevent long overflow lol
					}
				} else {
					dp[l][a] = dp[l+1][a];
				}
			}
		}
		
		System.out.println(search(0, 0, 0));
	}
	
	public static String search(int cur, int level, long lb) {
		if(lb + dp[level][cur][0] <= l || lb > r) return ""; 
		if(dp[level][cur][1] == n) return Character.toString('a' + (int)dp[level][cur][2]);
		
		
		StringBuilder sb = new StringBuilder();
		String to = op[(int)dp[level][cur][1]][(int)dp[level][cur][2]];
		for(int i = 0; i < to.length(); i++) {
			int l = to.charAt(i) - 'a';
			sb.append(search(l, (int)dp[level][cur][1]+1, lb));
			lb += dp[(int)dp[level][cur][1]+1][l][0];
			if(lb > r) break; // prevent long overflow lol
		}

		return sb.toString();
	}
}
