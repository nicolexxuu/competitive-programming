// 2016 ACM Anman CPC - D. Rectangles
// https://codeforces.com/gym/101102/problem/D
import java.util.*;
import java.io.*;

public class Rectangles {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
			int[][] a = new int[R+1][C+1];
			int[][] rstreak = new int[R+1][C+1];
			for(int i = 1; i <= R; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= C; j++) {
					a[i][j] = Integer.parseInt(st.nextToken());
					rstreak[i][j] = (a[i][j] == a[i][j-1] ? rstreak[i][j-1] : 0) + 1;
				}
			}
			
			long res = 0;
			
			for(int j = 1; j <= C; j++) {
				ArrayDeque<int[]> d = new ArrayDeque<>();
				
				int currCon = 0;
				for(int i = 1; i <= R; i++) {
					if(a[i][j] != a[i-1][j]) {
						d.clear();
						currCon = 0;
					} 
					
					int lastR = i;
					while(!d.isEmpty() && d.peek()[0] > rstreak[i][j]) {
						int[] curr = d.pop();
						currCon -= (curr[0] - rstreak[i][j]) * (lastR - curr[1]); // +1?
						lastR = curr[1];
					}
					
					currCon += rstreak[i][j];
					d.addFirst(new int[] {rstreak[i][j], lastR});
					res += currCon;
				}
			}
			
			System.out.println(res);
		}
		br.close();
	}
}