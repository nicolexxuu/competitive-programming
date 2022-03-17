import java.util.*;
import java.io.*;

public class braceletcrossings {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer>[] colors = new ArrayList[M];
			boolean valid = true;
			
			boolean[][] mark = new boolean[M][N+1];
			int[] lastSeen = new int[N+1];
			Arrays.fill(lastSeen, -1);
			int[] parent = new int[N+1];
			Arrays.fill(parent, -1);
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int k = Integer.parseInt(st.nextToken());
				colors[i] = new ArrayList<>();
				Stack<Integer> stk = new Stack<>();
				for(int j = 0; j < k; j++) {
					int c = Integer.parseInt(st.nextToken());
					colors[i].add(c);
					
					// condition 1 - check for continuity of colors
					if(lastSeen[c] != -1 && lastSeen[c] < i-1) valid = false;
					lastSeen[c] = i;
					
					// condition 2 - valid topology (())
					if(mark[i][c]) {
						if(stk.pop() != c) valid = false;
					} else {
						// condition 3 - color's parent is consistent
						int par = stk.isEmpty() ? 0 : stk.peek();
						if(parent[c] != -1 && parent[c] != par) valid = false;
						parent[c] = par;
						stk.push(c);
					}
					
					
					
					mark[i][c] = true;
				}
			}
			
			// condition 4 - consistent order of colors
			for(int b1 = 0; b1 < M; b1++) {
				for(int b2 = b1+1; b2 < M; b2++) {
					ArrayList<Integer> i1 = new ArrayList<>();
					ArrayList<Integer> i2 = new ArrayList<>();
					for(int c : colors[b1]) if(mark[b2][c]) i1.add(c);
					for(int c : colors[b2]) if(mark[b1][c]) i2.add(c);
					
					valid &= i1.equals(i2);
				}
			}
			
			if(valid) System.out.println("YES");
			else System.out.println("NO");
		}
		
		br.close();
	}
}