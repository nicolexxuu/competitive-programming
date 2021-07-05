// Game of Lines

import java.util.*;
import java.io.*;

public class lines {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] points = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		HashSet<Slope> slopes = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				slopes.add(new Slope(points[i][1] - points[j][1], points[i][0] - points[j][0]));
			}
		}
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(slopes.size());
		//out.println();
		//out.close();
	}
	
	public static class Slope {
		int n, d;
		
		Slope(int n, int d) {
			if(d < 0) {n *= -1; d *= -1;}
			
			int gcd = gcd(Math.abs(n), Math.abs(d));
			n /= gcd;
			d /= gcd;
			
			if(d == 0) n = -1;
			if(n == 0) d = -1;
			
			this.n = n; 
			this.d = d;
		}
		
		public int hashCode() {
			return n * 17 + d * 31;
		}
		
		public boolean equals(Object other) {
			return n == ((Slope)other).n && d == ((Slope)other).d;
		}
	}
	
	public static int gcd (int a, int b) {
		if(a == 0) return b;
		if(b == 0) return a;
		
		if(a >= b) return gcd(a%b, b);
		return gcd(a, b%a);
	}
}
