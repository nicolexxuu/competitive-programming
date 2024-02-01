import java.util.*;
import java.io.*;

public class lasers {
	public static void main(String[] args) throws IOException {
		String file = "lasers";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		HashMap<Integer, ArrayList<int[]>> xs = new HashMap<>(), ys = new HashMap<>();
		ArrayDeque<int[]> toVisit = new ArrayDeque<>();
		int xl = Integer.parseInt(st.nextToken()), yl = Integer.parseInt(st.nextToken());
		toVisit.add(new int[] {xl, yl, 0});
		int xb = Integer.parseInt(st.nextToken());
		int yb = Integer.parseInt(st.nextToken());
		xs.put(xb, new ArrayList<>(Arrays.asList(new int[] {yb, N})));
		ys.put(yb, new ArrayList<>(Arrays.asList(new int[] {xb, N})));

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
			xs.putIfAbsent(x, new ArrayList<>());
			ys.putIfAbsent(y, new ArrayList<>());
			xs.get(x).add(new int[] {y, i});
			ys.get(y).add(new int[] {x, i});
		}
		int[] dist = new int[N+1];
		Arrays.fill(dist, -1);
		
		br.close();

		while(!toVisit.isEmpty()) {
			int[] curr = toVisit.remove();
			int x = curr[0], y = curr[1], dst = curr[2];
			if(xs.containsKey(x)) {
				for(int[] to : xs.get(x)) {
					if(dist[to[1]] == -1) {
						dist[to[1]] = dst+1;
						toVisit.add(new int[] {x, to[0], dst+1});
					}
				}
			}
			xs.remove(x);
			
			if(ys.containsKey(y)) {
				for(int[] to : ys.get(y)) {
					if(dist[to[1]] == -1) {
						dist[to[1]] = dst+1;
						toVisit.add(new int[] {to[0], y, dst+1});
					}
				}
			}
			ys.remove(y);
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(dist[N]- (dist[N] == -1 ? 0 : 1));
		out.println(dist[N]- (dist[N] == -1 ? 0 : 1));
		out.close();
	}
}