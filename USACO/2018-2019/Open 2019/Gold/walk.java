// I Would Walk 500 Miles
import java.util.*;
import java.io.*;

public class walk {
	public static void main(String[] args) throws IOException {
		String file = "walk";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		br.close();
		
		boolean[] mark = new boolean[N+1];
		int[] dist = new int[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = dist[1] = 0;
		
		for(int in = 0; in < N; in++) {
			int minDist = Integer.MAX_VALUE;
			int minI = -1;
			
			for(int i = 1; i <= N; i++) {
				if(!mark[i] && dist[i] < minDist) {
					minI = i;
					minDist = dist[i];
				}
			}
			mark[minI] = true;
			
			for(int i = 1; i <= N; i++)
				if(!mark[i]) dist[i] = Math.min(dist[i], dist(Math.min(minI, i), Math.max(minI, i)));
		}
		
		Arrays.sort(dist);
		for(int i: dist) System.out.println(i);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(dist[N+2-K]);
		out.println(dist[N+2-K]);
		out.close();
	}
	
	public static int dist(int x, int y) { return (int)(((long)(2019201913) * x  + (long)(2019201949) * y ) % (long)2019201997); }
}
