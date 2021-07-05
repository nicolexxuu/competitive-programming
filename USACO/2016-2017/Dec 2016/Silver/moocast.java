
// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class moocast {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("moocast.in"));
		int N = in.nextInt();
		Cow[] cows = new Cow[N];
		for(int i = 0; i < N; i++) {
			cows[i] = new Cow(in.nextInt(), in.nextInt(), in.nextInt());
		}
		in.close();
		
		for(int c = 0; c < N; c++) {
			for(int co = 0; co < N; co++) {
				if(co == c) continue;
				Cow c1 = cows[c];
				Cow c2 = cows[co];
				if((c1.x - c2.x)* (c1.x - c2.x)+ (c1.y - c2.y)* (c1.y - c2.y) <= c1.p * c1.p) {
					c1.reachable.add(co);
				}
			}
		}
		
		int result = 0;
		for(int c = 0; c < N; c++) {
			boolean[] visited = new boolean[N];
			int temp = 0;
			LinkedList<Integer> list = new LinkedList<Integer>();
			list.add(c);
			while(!list.isEmpty()) {
				int curr = list.remove();
				if(visited[curr]) continue;
				visited[curr] = true;
				temp++;
				HashSet<Integer> leadsTo = cows[curr].reachable;
				for(int i: leadsTo) {
					list.add(i);
				}
			}
			
			result = Math.max(result, temp);
		}
		PrintWriter out = new PrintWriter(new File("moocast.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	public static class Cow {
		int x, y, p;
		HashSet<Integer> reachable = new HashSet<>();
		Cow (int xc, int yc, int pc){
			x = xc;
			y = yc;
			p = pc;
		}
	}
}
