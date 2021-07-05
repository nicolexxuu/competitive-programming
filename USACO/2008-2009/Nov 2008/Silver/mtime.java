// Time Management

import java.util.*;
import java.io.*;

public class mtime {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] jobs = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jobs[i][0] = Integer.parseInt(st.nextToken());
			jobs[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(jobs, (a, b) -> b[1] - a[1]);
		br.close();
		
		int latestTime = 1000000;
		for(int i = 0; i < N; i++) latestTime = Math.min(latestTime, jobs[i][1]) - jobs[i][0];
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		if(latestTime < 1) System.out.println(-1);
		else System.out.println(latestTime);
		//out.println();
		//out.close();
	}
}
