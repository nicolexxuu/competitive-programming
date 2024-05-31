import java.util.*;
import java.io.*;

public class friendship {
	static ArrayList<ArrayList<Integer>> adj;
	static int N;
	static int[] rt, sz;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		adj = new ArrayList<>();
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N; i++) adj.add(new ArrayList<>());
		int[] inDegree = new int[N];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			adj.get(u).add(v);
			adj.get(v).add(u);
			inDegree[u]++;
			inDegree[v]++;
		}
		
		br.close();
		boolean[] added = new boolean[N];
		boolean[] removed = new boolean[N];
		rt = new int[N];
		sz = new int[N];
		for(int i = 0; i < N; i++) {
			rt[i] = i;
			sz[i] = 1;
		}
		
		long res = 0;
		PriorityQueue<int[]> group = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		for(int i = 0; i < N; i++) group.add(new int[] {i, inDegree[i]});

		ArrayList<int[]> removedEdges = new ArrayList<>();

		while(group.size() > 0) {
			int[] curr = group.poll();
			if(removed[curr[0]]) continue;
			removed[curr[0]] = true;
			removedEdges.add(new int[] {curr[0], curr[1]});
			
			for(int to : adj.get(curr[0])) {
				if(!removed[to]) {
					inDegree[to]--;
					group.add(new int[] {to, inDegree[to]});
				}
			}
		}
		
		for(int j = removedEdges.size()-1; j >= 0; j--) {
			int id = removedEdges.get(j)[0], deg = removedEdges.get(j)[1];
			for(int to : adj.get(id))
				if(added[to]) merge(id, to);

			res = Math.max(res, deg * sz[rt[id]]);
			added[id] = true;
		}
		
		System.out.println(res);
	}
	
	public static int root(int a) {
		while(rt[a] != a) {
			rt[a] = rt[rt[a]];
			a = rt[a];
		}
		
		return a;
	}
	
	public static void merge(int a, int b) {
		int r_a = root(a), r_b = root(b);
		if(r_a == r_b) return;
		
		if(sz[r_a] < sz[r_b]) {
			rt[r_a] = r_b;
			sz[r_b] += sz[r_a];
		} else {
			rt[r_b] = r_a;
			sz[r_a] += sz[r_b];
		}
	}
}
