// Splitting the Field

import java.util.*;
import java.io.*;

public class split {
	public static long total = 1, min = Long.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		String file = "split";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Cow[] cows = new Cow[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		br.close();
		
		solve(cows, N);
		for(Cow c: cows) c.transpose();
		solve(cows, N);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(total - min);
		out.println(total - min);
		out.close();
	}
	
	public static class Cow {
		int x, y;
		
		Cow(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public void transpose() {
			int temp = x;
			x = y;
			y = temp;
		}
	}
	
	public static void solve(Cow[] cows, int N) {
		Arrays.sort(cows, (a, b) -> a.x - b.x);
		total *= cows[N-1].x - cows[0].x;
		TreeMap<Integer, Integer> right = new TreeMap<>(), left = new TreeMap<>();
		for(Cow c: cows) insert(right, c.y);
		
		for(int border = 0; border < N; border++) {
			if(border > 0) min = Math.min(min, (long)(cows[border - 1].x - cows[0].x) * (left.lastKey() - left.firstKey()) + (long)(cows[N - 1].x - cows[border].x) * (right.lastKey() - right.firstKey()));
			insert(left, cows[border].y);
			erase(right, cows[border].y);
		}
	}
	
	public static void insert(TreeMap<Integer, Integer> map, int a) {
		if(map.containsKey(a)) map.replace(a, map.get(a), map.get(a) + 1);
		else map.put(a, 1);
	}
	
	public static void erase(TreeMap<Integer, Integer> map, int a) {
		if(map.get(a) > 1) map.replace(a, map.get(a) - 1);
		else map.remove(a);
	}
}
