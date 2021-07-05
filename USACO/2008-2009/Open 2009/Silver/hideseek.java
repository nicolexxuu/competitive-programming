// Hide and Seek

import java.util.*;
import java.io.*;

public class hideseek {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		for(int i = 0; i < N; i++) adj.add(new ArrayList<>());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		br.close();
		
		int bestBarn = 0;
		int count = 0;
		int maxDist = 0;
		ArrayDeque<int[]> toVisit = new ArrayDeque<>();
		boolean[] visited = new boolean[N];
		visited[0] = true;
		toVisit.add(new int[] {0, 0});
		
		while(!toVisit.isEmpty()) {
			int[] curr = toVisit.remove();
			int barn = curr[0], dist = curr[1];
			if(dist > maxDist) {
				maxDist = dist;
				bestBarn = barn;
				count = 1;
			} else if(dist == maxDist) {
				bestBarn = Math.min(bestBarn, barn);
				count++;
			}
			
			for(int to: adj.get(barn)) {
				if(!visited[to]) {
					visited[to] = true;
					toVisit.add(new int[] {to, dist+1});
				}
			}
		}
		
		System.out.println((bestBarn+1) + " " + maxDist + " " + count);

	}
	
}
