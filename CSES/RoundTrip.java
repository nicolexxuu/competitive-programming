import java.util.*;
import java.io.*;

public class RoundTrip {
	static ArrayList<Integer> cycle = new ArrayList<>();
	static ArrayList<Integer>[] adj;
	static boolean[] mark;
	
	public static boolean dfs(int curr, int p) {
		cycle.add(curr);
		if(mark[curr]) return true;
		mark[curr] = true;
		
		for(int to : adj[curr]) {
			if(to == p) continue;
			if(dfs(to, curr)) return true;
		}
		
		cycle.remove(cycle.size()-1);
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n];
		mark = new boolean[n];
		for(int i = 0; i < n; i++) adj[i] = new ArrayList<>();
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj[a].add(b);
			adj[b].add(a);
		}
		br.close();
		
		boolean ans = false;
		for(int i = 0; i < n; i++) {
			if(mark[i]) continue;
			if(dfs(i, -1)) {
				ans = true;
				ArrayList<Integer> res = new ArrayList<>();
				for(int j = cycle.size()-1; j >= 0; j--) {
					res.add(cycle.get(j));
					if(j < cycle.size()-1 && (int)cycle.get(j) == res.get(0)) break;
				}
				System.out.println(res.size());
				for(int j : res) System.out.print(j+1 + " ");
				System.out.println();
			}
			if(ans) break;
		}
		
		if(!ans) System.out.println("IMPOSSIBLE");
	}
}