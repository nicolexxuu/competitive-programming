// "The Loathesome Hay Baler"

import java.util.*;
import java.io.*;

public class baler {
	static int first, last;
	static int[][] rollers;
	static int[][] adj;
	static boolean[] visited;
	static ArrayList<Integer> path;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X_t = Integer.parseInt(st.nextToken());
		int Y_t = Integer.parseInt(st.nextToken());
		
		rollers = new int[N][3];
		first = 0;
		last = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			rollers[i][0] = x;
			rollers[i][1] = y;
			rollers[i][2] = r;
			if(X_t == x && Y_t == y) last = i;
			if(x == 0 && y == 0) first = i;
		}

		adj = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				if((rollers[i][0] - rollers[j][0])*(rollers[i][0] - rollers[j][0]) + (rollers[i][1] - rollers[j][1])*(rollers[i][1] - rollers[j][1]) == (rollers[i][2] + rollers[j][2])*(rollers[i][2] + rollers[j][2])) {
					adj[i][j] = 1;
					adj[j][i] = 1;
				}
			}
		}
		
		br.close();
		visited = new boolean[N];
		ArrayList<Integer> temp = new ArrayList<>();
		temp.add(first);
		visited[first] = true;
		path = new ArrayList<>();
		dfs(first, temp);
		double sum = 10000;
		double preSpeed = 10000;
		for(int i = 1; i < path.size(); i++) {
			//System.out.println(path.get(i));
			preSpeed = -preSpeed * rollers[path.get(i-1)][2] / rollers[path.get(i)][2];
			sum += Math.abs(preSpeed);
		}
		
		System.out.println((int) sum);
	}
	
	public static void dfs(int curr, ArrayList<Integer> temp) {
		if(curr == last) {
			for(int i: temp) path.add(i);
			return;
		}
		for(int i = 0; i < adj.length; i++) {
			if(adj[curr][i] == 1 && !visited[i]) {
				visited[i] = true;
				temp.add(i);
				dfs(i, temp);
				temp.remove(temp.size()-1);
			}
		}
	}
}
