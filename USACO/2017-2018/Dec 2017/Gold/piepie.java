// A Pie for a Pie

import java.util.*;
import java.io.*;

public class piepie {
	public static void main(String[] args) throws IOException {
		String file = "piepie";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int[][] bessie = new int[N][2], elsie = new int[N][2];
		TreeSet<Pair> bSet = new TreeSet<>(), eSet = new TreeSet<>();
		ArrayDeque<int[]> toVisit = new ArrayDeque<>();
		int res[][] = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			res[i][0] = res[i][1] = -1;
			bessie[i][0] = Integer.parseInt(st.nextToken());
			bessie[i][1] = Integer.parseInt(st.nextToken());
			if(bessie[i][1] == 0) {
				toVisit.add(new int[] {i, 0});
				res[i][0] = 1;
			} else {
				bSet.add(new Pair(bessie[i][1], bessie[i][0], i));
			}
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			elsie[i][0] = Integer.parseInt(st.nextToken());
			elsie[i][1] = Integer.parseInt(st.nextToken());
			if(elsie[i][0] == 0) {
				toVisit.add(new int[] {i, 1});
				res[i][1] = 1;
			} else {
				eSet.add(new Pair(elsie[i][0], elsie[i][1], i));
			}
		}
		br.close(); 
		
		while(!toVisit.isEmpty()) {
			int i = toVisit.getFirst()[0], g = toVisit.remove()[1];
			if(g == 0) { 
				int bGift = bessie[i][0];
				while(eSet.higher(new Pair(bGift-D-1, -1, -1)) != null && eSet.higher(new Pair(bGift-D-1, -1, -1)).f <= bGift) {
					Pair rem = eSet.higher(new Pair(bGift-D-1, -1, -1));
					res[rem.i][1] = res[i][0] + 1; 
					eSet.remove(rem);
					toVisit.add(new int[] {rem.i, 1});
				}
			} else {
				int eGift = elsie[i][1];
				while(bSet.higher(new Pair(eGift-D-1, -1, -1)) != null && bSet.higher(new Pair(eGift-D-1, -1, -1)).f <= eGift) {
					Pair rem = bSet.higher(new Pair(eGift-D-1, -1, -1));
					res[rem.i][0] = res[i][1] + 1;
					bSet.remove(rem);
					toVisit.add(new int[] {rem.i, 0});
				}
			}
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		for(int i = 0; i < N; i++) out.println(res[i][0]);

		out.close();
	}
	
	public static class Pair implements Comparable<Pair> {
		int f, s;
		int i;
		Pair(int f, int s, int i) {
			this.f = f;
			this.s = s;
			this.i = i;
		}

		public int compareTo(Pair other) { 
			return f == other.f ? other.i - i : f - other.f; // allow cows with duplicate tastiness values
		}
		
		public String toString() { return "f: "  + f + " s: " + s + " i: " + i; }
	}
}
