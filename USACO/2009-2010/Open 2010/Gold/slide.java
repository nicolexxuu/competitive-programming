// Water Slides

import java.util.*;
import java.io.*;

public class slide {
	static ArrayList<ArrayList<Edge>> adj;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList<>();
		for(int i = 0; i < V; i++) adj.add(new ArrayList<>());
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			adj.get(Integer.parseInt(st.nextToken())-1).add(new Edge(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
		}
		br.close();
		
		long[][] dp = new long[V][K+1];
		System.out.println(maxFun(0, K, dp));
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		//out.println(result);
		//out.close();
	}
	
	public static long maxFun(int curr, int slips, long[][] dp) {
		if(dp[curr][slips] > 0) return dp[curr][slips];
		long ret = 0;
			
		// no slips
		for(Edge e: adj.get(curr)) {
			ret = Math.max(ret, maxFun(e.to, slips, dp) + e.weight);
		}
		
		// with slips
		if(slips > 0)
		for(Edge e: adj.get(curr)) {
			ret = Math.min(ret, maxFun(e.to, slips-1, dp) + e.weight);
		}
		
		dp[curr][slips] = ret;
		return ret;
	}
	
	public static class Edge {
		int to, weight;
		Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
}

