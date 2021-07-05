// Bessie's Birthday Buffet

import java.util.*;
import java.io.*;

public class buffet {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		Node[] patches = new Node[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int Q = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			patches[i] = new Node(i, Q, D);
			for(int j = 0; j < D; j++) patches[i].adj.add(Integer.parseInt(st.nextToken())-1);

		}
		br.close();
		
		int[] newLoc = new int[N];
		int[][] dist = new int[N][N];
		for(int[] a: dist) Arrays.fill(a, Integer.MAX_VALUE);
		
		Arrays.sort(patches, (a, b) -> a.Q - b.Q);
		for(int j = 0; j < N; j++) newLoc[patches[j].id] = j;
		
		
		// bfs! :)
		for(int i = 0; i < N; i++) {
			ArrayDeque<Integer> toVisit = new ArrayDeque<>();
			toVisit.add(i);
			dist[i][i] = 0;
			
			while(!toVisit.isEmpty()) {
				int curr = toVisit.remove();
				for(int to: patches[curr].adj) {
					int n = newLoc[to];
					if(dist[i][n] == Integer.MAX_VALUE) {
						dist[i][n] = dist[i][curr]+1;
						toVisit.add(n);
					}
				}
			}
		}
		
		int result = 0;
		int[] dp = new int[N]; // after eating grass at this node; try to come from other nodes
		for(int j = 0; j < N; j++) {
			dp[j] = patches[j].Q;
			for(int i = 0; i < j; i++) {
				if(dist[i][j] == Integer.MAX_VALUE) continue;
				else dp[j] = Math.max(dp[j], dp[i] - E*dist[i][j] + patches[j].Q);
			}
			result = Math.max(result, dp[j]);
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
	public static class Node {
		int id, Q, D;
		ArrayList<Integer> adj = new ArrayList<>();
		
		Node(int id, int Q, int D) {
			this.id = id;
			this.Q = Q;
			this.D = D;
		}
	}


}
