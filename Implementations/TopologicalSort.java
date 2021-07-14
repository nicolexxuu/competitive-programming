// Topological Sort Implementation (for DAGs)
// O(N)

import java.util.*;
import java.io.*;

public class TopologicalSort {
	static int[] inDegree;
	static int N;
	static ArrayList<ArrayList<Integer>> adj;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) adj.add(new ArrayList<>());
		for(int i = 0; i < M; i++) {
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj.get(a).add(b);
			inDegree[b]++;
		}
		
		br.close();
		ArrayDeque<Integer> toVisit = new ArrayDeque<>();
		
		for(int i = 0; i < N; i++) if(inDegree[i] == 0) toVisit.add(i);
		
		// if iterations < N: graph is cyclic
		while(!toVisit.isEmpty()) {
			int curr = toVisit.remove();
			
			System.out.println(curr+1);
			for(int to : adj.get(curr)) {
				inDegree[to]--;
				if(inDegree[to] == 0) toVisit.add(to);
			}
			
		}

	}

}
