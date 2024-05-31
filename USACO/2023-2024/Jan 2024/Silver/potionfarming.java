import java.util.*;
import java.io.*;

public class potionfarming {
	static int trav;
	static int[] poss, p, pot;
	static ArrayList<Integer>[] adj;
	static int dfs(int curr, int par) {
		int visited = 0; 
		
		poss[curr] = 0;
		for(int to : adj[curr]) {
			if(to == par) continue;
			visited += dfs(to, curr);
			poss[curr] += poss[to];
		}
		poss[curr] = Math.max(poss[curr], 1);
		return Math.min(poss[curr], visited + pot[curr]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		p = new int[N]; 
		pot = new int[N];
		poss = new int[N];
		adj = new ArrayList[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			p[i] = Integer.parseInt(st.nextToken())-1;
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1, b = Integer.parseInt(st.nextToken())-1;
			adj[a].add(b);
			adj[b].add(a);
		}
		br.close();
			
		dfs(0, -1);
		for(int i = 0; i < poss[0]; i++) pot[p[i]]++;
		System.out.println(dfs(0, -1));
	}
}