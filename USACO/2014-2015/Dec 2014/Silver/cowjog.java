import java.util.*;
import java.io.*;

//Cow Jog: 2014 December, Silver
//http://www.usaco.org/index.php?page=viewproblem2&cpid=493
public class cowjog {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("10.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int[][] cows = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i][0] = Integer.parseInt(st.nextToken());
			cows[i][1] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		int[] endingPos = new int[N];
		for(int i = 0; i < N; i++) {
			endingPos[i] = cows[i][0] + cows[i][1] * T;
		}
		long groups = 0;
		long curr = Long.MAX_VALUE;
		for(int cow = N - 1; cow >= 0; cow--) {
			if(endingPos[cow] < curr) {
				groups++;
				curr = endingPos[cow];
				System.out.println(cow);
			}
		}
		PrintWriter out = new PrintWriter(new File("cowjog.out"));
		System.out.println(groups);
		out.println(groups);
		out.close();
	}
}
