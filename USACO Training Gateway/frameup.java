// Frame Up

import java.util.*;
import java.io.*;

public class frameup {
	public static char[] names;
	public static ArrayList<String> combos = new ArrayList<String>();
	public static int N;
	public static void main(String[] args) throws IOException {
		String file = "frameup";
		//BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int[][] grid = new int[H][W];
		names = new char[26];
		int[][] dim = new int[26][4];
		for(int i = 0; i < 26; i++) {
			dim[i][0] = Integer.MAX_VALUE;
			dim[i][1] = Integer.MAX_VALUE;
		}
		int ind = 0;
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			char[] A = st.nextToken().toCharArray();
			for(int j = 0; j < W; j++) {
				if(A[j] == '.') {
					grid[i][j] = -1;
					continue;
				}
				char c = A[j];
				int set = -1;
				for(int k = 0; k < ind; k++) {
					if(names[k] == c) {
						set = k;
						break;
					}
				}
				if(set == -1) {
					set = ind;
					names[ind] = c;
					ind++;
				}
				grid[i][j] = set;
				dim[set][0] = Math.min(dim[set][0], i);
				dim[set][1] = Math.min(dim[set][1], j);
				dim[set][2] = Math.max(dim[set][2], i);
				dim[set][3] = Math.max(dim[set][3], j);
			}
		}
		N = ind;
		br.close();
		
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		int[] inDegree = new int[N];
		
		for(int from = 0; from < N; from++) {
			adj.add(new ArrayList<>());
			for(int i = dim[from][0]; i <= dim[from][2]; i++) {
				int j = dim[from][1];
				if(grid[i][j] >= 0 && grid[i][j] != from && !adj.get(from).contains(grid[i][j])) {
					inDegree[grid[i][j]]++;
					adj.get(from).add(grid[i][j]);
				}
				
				j = dim[from][3];
				if(grid[i][j] >= 0 && grid[i][j] != from && !adj.get(from).contains(grid[i][j])) {
					inDegree[grid[i][j]]++;
					adj.get(from).add(grid[i][j]);
				}
			}
			
			for(int j = dim[from][1]; j <= dim[from][3]; j++) {
				int i = dim[from][0];
				if(grid[i][j] >= 0 && grid[i][j] != from && !adj.get(from).contains(grid[i][j])) {
					inDegree[grid[i][j]]++;
					adj.get(from).add(grid[i][j]);
				}
				
				i = dim[from][2];
				if(grid[i][j] >= 0 && grid[i][j] != from && !adj.get(from).contains(grid[i][j])) {
					inDegree[grid[i][j]]++;
					adj.get(from).add(grid[i][j]);
				}
			}
		}
		compute(new ArrayList<Integer>(), inDegree, adj);
		Collections.sort(combos);
		
		
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		for(String s: combos) System.out.println(s);
		//out.close();
	}
	
	public static void compute(ArrayList<Integer> seq, int[] inDegree, ArrayList<ArrayList<Integer>> adj) {
		if(seq.size() == N) {
			makeSeq(seq);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(inDegree[i] == 0) {
				inDegree[i]--;
				seq.add(i);
				for(int to: adj.get(i)) inDegree[to]--;
				
				compute(seq, inDegree, adj);
				
				seq.remove(seq.size()-1);
				for(int to: adj.get(i)) inDegree[to]++;
				inDegree[i]++;
			}
		}
	}
	
	public static void makeSeq(ArrayList<Integer> seq) {
		String s = "";
		for(int i: seq) s+=names[i];
		combos.add(s);
	}
}

