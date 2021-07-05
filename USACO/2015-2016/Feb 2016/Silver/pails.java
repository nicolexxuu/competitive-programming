// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=620
// "Milk Pails"
import java.util.*;
import java.io.*;

public class pails {
	
	static int[][] visited;
	static int X, Y, K, M;
	static int result;
	public static void main(String[] args) throws IOException {
		String file = "pails";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = M;
		br.close();
		
		visited = new int[X + 1][Y + 1];
		for(int i = 0; i < visited.length; i++) {
			for(int j = 0; j < visited[i].length; j++) {
				visited[i][j] = K + 1;
			}
		}
		simulate(0, 0, 1);
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	public static void simulate(int a, int b, int step) {
		if(step <= K && result != 0) {
			if(step < visited[X][b]) {
				visited[X][b] = step;
				result = Math.min(result, Math.abs(M - (X + b)));
				simulate(X, b, step + 1);
			}
			
			if(step < visited[a][Y]) {
				visited[a][Y] = step;
				result = Math.min(result, Math.abs(M - (a + Y)));
				simulate(a, Y, step + 1);
			}
			
			if(step < visited[0][b]) {
				visited[0][b] = step;
				result = Math.min(result, Math.abs(M - b));
				simulate(0, b, step + 1);
			}
			
			if(step < visited[a][0]) {
				visited[a][0] = step;
				result = Math.min(result, Math.abs(M - a));
				simulate(a, 0, step + 1);
			}
			
			int newA = Math.min(X, a + b);
			int newB = Math.max(0, b - (X - a));
			if(step < visited[newA][newB]) {
				visited[newA][newB] = step;
				result = Math.min(result, Math.abs(M - (newA + newB)));
				simulate(newA, newB, step + 1);
			}
			
			newA = Math.max(0, a - (Y - b));
			newB = Math.min(Y, a + b);
			if(step < visited[newA][newB]) {
				visited[newA][newB] = step;
				result = Math.min(result, Math.abs(M - (newA + newB)));
				simulate(newA, newB, step + 1);
			}
		}
	}
}

