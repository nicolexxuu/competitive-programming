import java.util.*;
import java.io.*;

public class moonetwork {
	static int N;
	static int[] rt, sz;
	public static void main(String[] args) throws IOException {
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int[][] points = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
		}
		rt = new int[N];
		sz = new int[N];
		init();
		
		Arrays.sort(points, (a, b) -> a[0] - b[0]);
		int[] last = new int[11];
		Arrays.fill(last, -1);
		PriorityQueue<Edge> toVisit = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			for(int y = 0; y <= 10; y++) {
				if(last[y] >= 0) toVisit.add(new Edge(last[y], i, dist(points[last[y]][0], points[last[y]][1], points[i][0], points[i][1])));
			}
			
			last[points[i][1]] = i;
		}
		
		long total = 0;
		while(!toVisit.isEmpty()) {
			Edge e = toVisit.poll();
			
			if(root(e.a) != root(e.b)) {
				total += e.weight;
				merge(e.a, e.b);
			}
		}
		
		System.out.println(total);
		br.close();
	}
	
	public static long dist(long x1, long y1, long x2, long y2) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}
	
	public static void init() {
		for(int i = 0; i < N; i++) {
			rt[i] = i;
			sz[i] = 1;
		}
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
	
	public static class Edge implements Comparable<Edge>{
		int a, b;
		long weight; 
		
		Edge(int a, int b, long weight) {
			this.a = a;
			this.b = b;
			this.weight = weight;
		}
		
		public int compareTo(Edge other) {
			return Long.compare(weight, other.weight);
		}
	}
}