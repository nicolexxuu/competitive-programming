// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

import java.util.*;
import java.io.*;

public class milkvisits {
	public static void main(String[] args) throws IOException {
		String file = "milkvisits";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Farm[] farms = new Farm[N + 1]; //1- holstein, 2-guernsey
		for(int i = 1; i <= N; i++) {
			farms[i] = new Farm();
		}
		
		st = new StringTokenizer(br.readLine());
		char[] milk = st.nextToken().toCharArray();
		for(int i = 0; i < N; i++) {
			if(milk[i] == 'H') farms[i + 1].milk = 1;
		}
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			farms[X].leadsTo.add(Y);
			farms[Y].leadsTo.add(X);
		}
		
		//floodfill
		int c = 1;
		for(int i = 1; i <= N; i++) {
			if(farms[i].cluster == 0) {
				farms[i].cluster = c;
				LinkedList<Integer> list = new LinkedList<>();
				list.add(i);
				while(!list.isEmpty()) {
					int curr = list.remove();
					int m = farms[curr].milk;
					HashSet<Integer> path = farms[curr].leadsTo;
					for(int p: path) {
						if(farms[p].cluster == 0 && farms[p].milk == m) {
							farms[p].cluster = c;
							list.add(p);
						}
					}
				}
				c++;
			}
		}
		
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = 0;
			char m = st.nextToken().charAt(0);
			if(m == 'H') C = 1;
		
			if(farms[A].cluster != farms[B].cluster || farms[A].milk == C) {
				out.print(1);
			} else {
				out.print(0);
			}
			
		}	
		
		br.close();
		out.println();
		out.close();
	}
	
	static class Farm {
		int milk, cluster;
		HashSet<Integer> leadsTo;
		Farm(){
			milk = 0;
			cluster = 0;
			leadsTo = new HashSet<>();
		}
	}
}
