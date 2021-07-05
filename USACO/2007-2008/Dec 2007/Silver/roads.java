// Building Roads

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class roads {
	static int[] x, y;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		x = new int[N];
		y = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		double[][] adj = new double[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				adj[i][j] = distance(i, j);
			}
		}
		double[] dist = new double[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		int inTree = 0;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			adj[a][b] = 0;
			adj[b][a] = 0;
		}
		br.close();
		
		boolean[] visited = new boolean[N];
		dist[0] = 0;
		double result = 0;
		
		while(inTree < N) {
			int minInd = 0;
			double minDist = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				if(!visited[i] && dist[i] < minDist) {
					minDist = dist[i];
					minInd = i;
				}
			}
			
			visited[minInd] = true;
			result += minDist;
			
			for(int i = 0; i < N; i++) {
				if(!visited[i]) dist[i] = Math.min(dist[i], adj[i][minInd]);
			}
			
			inTree++;
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		DecimalFormat df = new DecimalFormat("#.00");
		System.out.println(df.format(result));
		//out.println(result);
		//out.close();
	}
	
	public static double distance(int p1, int p2) {
		return Math.sqrt((long)(x[p1] - x[p2])*(x[p1] - x[p2]) + (long)(y[p1]-y[p2])*(y[p1]-y[p2]));
	}
}
