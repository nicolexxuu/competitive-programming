// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

//http://www.usaco.org/index.php?page=viewproblem2&cpid=765
//"A Pie for a Pie", 2017 December Gold Contest

import java.util.*;
import java.io.*;

public class piepie {
	public static void main(String[] args) throws IOException {
		String file = "piepie";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		ArrayDeque<int[]> toVisit = new ArrayDeque<int[]>();
		
		Pie[] bessie = new Pie[N]; //pies given to elsie
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			bessie[i] = new Pie(i, b, e);
		}
		
		Pie[] elsie = new Pie[N];  //pies given to bessie
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			elsie[i] = new Pie(i, b, e);
		}
		
		Arrays.sort(bessie, (x, y) -> x.b - y.b);
		Arrays.sort(elsie, (x, y) -> x.e - y.e);
		
		for(int i = 0; i < N; i++) {
			if(bessie[i].e == 0) {
				toVisit.add(new int[] {i, 0, 1}); //0 - bessie, 1 - elsie
			}
			
			if(elsie[i].b == 0) {
				toVisit.add(new int[] {i, 1, 1});
			}
		}
		
		br.close();

		while(!toVisit.isEmpty()) {
			int[] curr = toVisit.remove();
			int i = curr[0];
			System.out.println(i);
			
			boolean b = curr[1] == 0;
			
			Pie c;
			if(b) c = bessie[i];
			else c = elsie[i];
			int steps = curr[2];
			System.out.println(c.b + " " + c.e);
			
			int l = 0, r = 0;
			if(b) {
				System.out.println("searching for " + (c.b - D) + " to " + c.b);
				l = bs(elsie, c.b - D, false);
				r = bs(elsie, c.b, false);
				System.out.println(l + " " + r);
				while(l <= r && l < N) {
					if(elsie[l].b <= c.b && elsie[l].b >= c.b - D && elsie[l].steps > steps + 1) {
						elsie[l].steps = steps + 1;
						toVisit.add(new int[] {l, 1, steps + 1});
					}
					l++;
				}
			} else {
				System.out.println("searching for " + (c.e - D) + " to " + c.e);
				l = bs(bessie, c.e - D, true);
				r = bs(bessie, c.e, true);
				while(l <= r && l < N) {
					if(bessie[l].e <= c.e && bessie[l].e >= c.e - D && bessie[l].steps > steps + 1) {
						bessie[l].steps = steps + 1;
						toVisit.add(new int[] {l, 0, steps + 1});
					}
					l++;
				}
			}
		}
		
		int[] result = new int[N];
		for(int j = 0; j < N; j++) {
			if(bessie[j].steps < Integer.MAX_VALUE) result[bessie[j].id] = bessie[j].steps;
			else result[bessie[j].id] = -1;
		}
		
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		for(int i = 0; i < N; i++) {
			int val = result[i];
			if(val == Integer.MAX_VALUE) {
				out.println(-1);
				System.out.println(-1);
			} else {
				out.println(val);
				System.out.println(val);
			}
		}
		out.close();
	}
	
	static class Pie {
		int id;
		int b, e;
		int steps;
		
		Pie(int id, int b, int e){
			this.id = id;
			this.b = b;
			this.e = e;
			steps = Integer.MAX_VALUE;
		}
	}
	
	public static int bs(Pie[] A, int target, boolean bessie) {
		
		if(bessie) {
			int low = 0;
			int high = A.length - 1;
			while (low < high) {
				int mid = (low + high) / 2;
				if (A[mid].b >= target) {
					high = mid;
				} else {
					low = mid + 1;
				}
			}
	
			return low;
		}
		
		int low = 0;
		int high = A.length - 1;
		while (low < high) {
			int mid = (low + high) / 2;
			if (A[mid].e >= target) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}

		return low;
	}
}

