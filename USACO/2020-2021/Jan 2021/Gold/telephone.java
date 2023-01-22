// 

import java.util.*;
import java.io.*;

public class telephone {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] b = new int[N];
		ArrayList<Integer>[] loc = new ArrayList[K];
		for(int i = 0; i < K; i++) loc[i] = new ArrayList<>();
		int[][] last = new int[N][K], first = new int[N][K];
		for(int[] a : last) Arrays.fill(a, -1);
		for(int[] a : first) Arrays.fill(a, -1);
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			b[i] = Integer.parseInt(st.nextToken())-1;
			loc[b[i]].add(i);
			if(i == N-1) break;
			for(int k = 0; k < K; k++) last[i+1][k] = last[i][k];
			last[i+1][b[i]] = i;
		}
		
		for(int i = N-1; i > 0; i--) {
			for(int k = 0; k < K; k++) first[i-1][k] = first[i][k];
			first[i-1][b[i]] = i;
		}
		
		boolean[][] S = new boolean[K][K];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			char[] s = st.nextToken().toCharArray();
			for(int j = 0; j < K; j++) S[i][j] = s[j] == '1';
		}
		br.close();
		
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		PriorityQueue<Edge> toVisit = new PriorityQueue<>();
		toVisit.add(new Edge(0, 0));
		while(!toVisit.isEmpty()) {
			Edge c = toVisit.remove();
			int id = c.to;
			if(dist[id] != c.weight) continue;
			
			for(int breed = 0; breed < K; breed++) {
				if(S[b[id]][breed] || (breed == b[id] && id != 0)) {
					int j = last[id][breed], k = first[id][breed];
					
					if(j != -1 && dist[id] + (id-j) < dist[j]) {
						dist[j] = dist[id] + (id-j);
						toVisit.add(new Edge(j, dist[j]));
					}
					
					if(k != -1 && dist[id] + (k-id) < dist[k]) {
						dist[k] = dist[id] + (k-id);
						toVisit.add(new Edge(k, dist[k]));
					}
				}
			}
		}
		
		if(dist[N-1] == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(dist[N-1]);
	}
	
	public static class Edge implements Comparable<Edge> {
		int to, weight;
		Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
		
		public int compareTo(Edge other) {
			return weight - other.weight;
		}
	}
}