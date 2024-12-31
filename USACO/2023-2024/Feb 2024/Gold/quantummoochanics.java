import java.util.*;
import java.io.*;

public class quantummoochanics {
	static long[] p, s;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] adj = new int[N][2];
			p = new long[N];
			s = new long[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) p[i] = Long.parseLong(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) s[i] = Integer.parseInt(st.nextToken());
			
			long[] res = new long[N];
			PriorityQueue<Pair> toVisit = new PriorityQueue<>();
			for(int i = 0; i < N; i++) {
				adj[i] = new int[]{i-1, i+1};
				if(i < N-1) toVisit.add(new Pair(i, i+1));
			}
			
			while(!toVisit.isEmpty()) {
				Pair curr = toVisit.remove();
				if(res[curr.a] != 0 || res[curr.b] != 0) continue;
				res[curr.a] = res[curr.b] = curr.obsnum;
				int l = adj[curr.a][0], r = adj[curr.b][1];
				if(l >= 0 && r < N) {
					adj[l][1] = r;
					adj[r][0] = l;
					toVisit.add(new Pair(l, r));
				}
			}
			
			for(int i = 0; i < N; i++) {
				System.out.print(res[i]);
				if(i < N-1) System.out.print(" ");
			}
			System.out.println();
		}
		br.close();
	}
	
	static class Pair implements Comparable<Pair> {
		int a, b;
		long obsnum;
		
		Pair(int a, int b) {
			this.a = a;
			this.b = b;
			this.obsnum = (long) Math.ceil((double)(p[b] - p[a]) / (s[a] + s[b]));
			this.obsnum = this.obsnum * 2 - (a % 2 == 0 ? 1 : 0);
		}
		
		public int compareTo(Pair other) {
			return Long.compare(obsnum, other.obsnum);
		}
	}
}