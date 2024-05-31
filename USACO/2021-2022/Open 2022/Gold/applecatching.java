// Apple Catching 

import java.util.*;
import java.io.*;

public class applecatching {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		ArrayList<Item> A = new ArrayList<>(), C = new ArrayList<>();
		TreeSet<Item> cows2 = new TreeSet<>((a, b) -> a.x == b.x ? Integer.compare(b.y, a.y) : a.x - b.x);
														// order of ys doesn't matter; just preventing duplicate entries
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			int u = x-t, v = x+t;
			if(q == 1) C.add(new Item(u, v, n));
			else A.add(new Item(u, v, n));
		}
		br.close();
		
		Collections.sort(A);
		Collections.sort(C);
		
		int res = 0;
		int currC = 0;
		
		for(Item a : A) {
			while(currC < C.size() && C.get(currC).y <= a.y) {
				cows2.add(C.get(currC));
				currC++;
			}
			
			while(a.n > 0 && cows2.ceiling(new Item(a.x, Integer.MAX_VALUE, 0)) != null) {
				Item c = cows2.ceiling(new Item(a.x, Integer.MAX_VALUE, 0));
				
				int caught = Math.min(c.n, a.n);
				a.n -= caught;
				c.n -= caught;
				res += caught;

				if(c.n == 0) cows2.remove(c);
			}
		}
		
		System.out.println(res);
	}
	
	static class Item implements Comparable<Item>{
		int x, y, n;
		
		Item(int x, int y, int n) {
			this.x = x;
			this.y = y;
			this.n = n;
		}
		
		public int compareTo(Item other) {
			return (y == other.y) ? x - other.x : y - other.y;
		}
	}
}