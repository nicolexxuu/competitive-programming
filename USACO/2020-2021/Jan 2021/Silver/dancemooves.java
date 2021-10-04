// Dance Mooves

import java.util.*;
import java.io.*;

public class dancemooves {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] cows = new int[N+1];
		List<Integer>[] visited = new List[N+1];
		
		for(int i = 1; i <= N; i++) {
			cows[i] = i;
			visited[i] = new ArrayList<>();
			visited[i].add(i);
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = cows[a], d = cows[b];
			cows[a] = d;
			cows[b] = c;
			visited[d].add(a);
			visited[c].add(b);
		}
		
		br.close();
		int[] ans = new int[N+1];
		for(int c = 1; c <= N; c++) {
			if(cows[c] == 0) continue;
			
			ArrayList<Integer> cycle = new ArrayList<>();
			int idx = c;
			while(cows[idx] != 0) {
				cycle.add(idx);
				idx = cows[idx]; // travel edge backwards
				cows[cycle.get(cycle.size()-1)] = 0;
			}
			
			HashSet<Integer> viewed = new HashSet<>();
			for(int i : cycle) viewed.addAll(visited[i]);
			for(int i : cycle) ans[i] = viewed.size();
		}
		
		
		for(int i = 1; i <= N; i++) System.out.println(ans[i]);
	}
}