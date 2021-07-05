// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java

// http://www.usaco.org/index.php?page=viewproblem2&cpid=813
// "Snow Boots", 2018 February Gold Division

import java.util.*;
import java.io.*;

public class snowboots {
	public static void main(String[] args) throws IOException {
		String file = "snowboots";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		Tile[] tiles = new Tile[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			tiles[i] = new Tile(Integer.parseInt(st.nextToken()), i);
		}
		
		Boot[] boots = new Boot[B];
		for(int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			boots[i] = new Boot(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(tiles, (x, y) -> y.depth - x.depth);
		Arrays.sort(boots, new Comparator<Boot>() {
			public int compare(Boot a, Boot b){
				return b.depth - a.depth;
			}
		});
		
		int[] next = new int[N];
		int[] prev = new int[N];
		for(int i = 0; i < N; i++) {
			next[i] = i+1;
			prev[i] = i-1;
		}
		
		br.close();

		int[] result = new int[B];
		int maxDist = 1;  // greatest distance the boot has to handle (currently)
		int currTile = 0; // greatest tile that boot can handle
		
		//try all boots with decreasing depths
		for(int boot = 0; boot < B; boot++) {
			Boot b = boots[boot];
			while(currTile < N && tiles[currTile].depth > b.depth) {
				Tile t = tiles[currTile];
				maxDist = Math.max(maxDist, next[t.id] - prev[t.id]);
				// make other boots skip over it (adjust pointers)
				if(next[t.id] < N && next[t.id] >= 0) prev[next[t.id]] = prev[t.id];
				if(prev[t.id] < N && prev[t.id] >= 0) next[prev[t.id]] = next[t.id];	
				currTile++;
			}

			if(maxDist <= b.step) result[b.id] = 1;
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("snowboots.out"))));
		for(int i = 0; i < B; i++) {
			//System.out.println(result[i]);
			out.println(result[i]);
		}
		out.close();
	}
	
	public static class Tile {
		int depth;
		int id;
		
		Tile(int depth, int id){
			this.depth = depth;
			this.id = id;
		}
	}
	
	public static class Boot {
		int depth, step;
		int id;
		
		Boot(int id, int depth, int step){
			this.id = id;
			this.depth = depth;
			this.step = step;
		}
	}
}
	

