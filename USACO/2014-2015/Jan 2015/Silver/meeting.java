// Meeting Time

import java.util.*;
import java.io.*;

public class meeting {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
		for(int i = 0; i < N; i++) adj.add(new ArrayList<>());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			adj.get(b).add(new Edge(a, c, d));
		}
		
		boolean[][] dp1 = new boolean[N][N*100+1];
		boolean[][] dp2 = new boolean[N][N*100+1];
		br.close();
		
		dp1[0][0] = true;
		dp2[0][0] = true;
		
		for(int i = 0; i < N; i++)
			for(Edge e: adj.get(i)) 
				for(int time = 1; time <= N*100; time++) {
					if(e.first <= time) dp1[i][time] |= dp1[e.from][time - e.first];
					if(e.second <= time) dp2[i][time] |= dp2[e.from][time - e.second];
				}
		
		boolean found = false;
		for(int i = 1; i < N*100+1; i++)
			if(dp1[N-1][i] && dp2[N-1][i]) {
				System.out.println(i);
				found = true;
				break;
			}

		if(!found) System.out.println("IMPOSSIBLE");
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		//out.println(result);
		//out.close();
	}
	
	public static class Edge {
		int from;
		int first, second;
		
		Edge(int from, int first, int second) {
			this.from = from;
			this.first = first;
			this.second = second;
		}
	}
}
