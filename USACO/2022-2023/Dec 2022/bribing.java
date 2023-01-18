// 

import java.util.*;
import java.io.*;

public class bribing {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[][] friends = new int[N+1][3];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			friends[i][0] = Integer.parseInt(st.nextToken()); // P
			friends[i][1] = Integer.parseInt(st.nextToken()); // C
			friends[i][2] = Integer.parseInt(st.nextToken()); // X
		}

		int[][] dp2 = new int[N+1][A+B+1];
		br.close();
		
		Arrays.sort(friends, 1, N+1, (a,b) -> a[2] - b[2]);
		for(int i = 1; i <= N; i++) {
			for(int spent = 1; spent <= A+B; spent++) {
				dp2[i][spent] = dp2[i-1][spent];
			}
			
			for(int prevSpent = 0; prevSpent <= A+B; prevSpent++) {
				int cones = prevSpent >= B ? 0 : Math.min(friends[i][1] * friends[i][2], B - prevSpent);
				int money = friends[i][1] - cones/friends[i][2];
				if(prevSpent + cones + money <= A+B) {
					dp2[i][prevSpent+cones+money] = Math.max(dp2[i][prevSpent+cones+money], dp2[i-1][prevSpent] + friends[i][0]);
				}
			}
		}
		
		System.out.println(dp2[N][A+B]);
	}
}
