import java.util.*;
import java.io.*;

public class mooves {
	static int unique = 0;
	static int[] count;
	static ArrayList<Visit>[] visits;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		int[][] swaps = new int[K+1][2];
		int[] cow = new int[N+1];
		int[] pos = new int[N+1];
		count = new int[N+1];
		visits = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) {
			cow[i] = i;
			pos[i] = i;
			visits[i] = new ArrayList<Visit>();
			visits[i].add(new Visit(i, 0));
		}
		
		for(int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			swaps[i][0] = Integer.parseInt(st.nextToken());
			swaps[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// simulate first K swaps!
		for(int i = 1; i <= K; i++) {
			int a = swaps[i][0], b = swaps[i][1];
			int c = cow[a], d = cow[b];
			visits[c].add(new Visit(b, i));
			visits[d].add(new Visit(a, i));
			pos[c] = b;
			pos[d] = a;
			cow[a] = d;
			cow[b] = c;
		}
		
		int[] res = new int[N+1];
		boolean[] mark = new boolean[N+1];
		// compute for each unchecked cycle
		for(int c = 1; c <= N; c++) {
			if(mark[c]) continue;
			ArrayList<Integer> cycle = new ArrayList<Integer>();
			int curr = c;
			while(!mark[curr]) {
				cycle.add(curr);
				mark[curr] = true;
				curr = pos[curr];
			}
			
			
			long D = M / K; // number of full Ks
			int R = (int)(M % K); // remaining iterations for afterwards
			
			if(cycle.size() < D) { // all parts of cycle are covered
				D = cycle.size();
				R = 0;
			}

			// for this cycle, use a sliding window to determine the total unique positions starting
			// from each cow's original position
			
			// initialize window
			int end = (int)D-1; // window starts at [0, D-1]
			for(int i = 0; i <= end; i++) add(cycle.get(i), K);
			
			// slide window [i, end]
			for(int i = 0; i < cycle.size(); i++) {
				int next = (end + 1) % cycle.size();
				// finish up current window
				add(cycle.get(next), R);
				res[cycle.get(i)] = unique;
				// undo R and remove i
				remove(cycle.get(next), R);
				remove(cycle.get(i), K);
				// add up new end
				add(cycle.get(next), K);
				end = next;
			}
			
			// reset for next cycle
			for(int i = 0; i <= (int)D-1; i++) remove(cycle.get(i), K);
		}
		
		for(int i = 1; i <= N; i++) System.out.println(res[i]);
		
	}
	
	public static void add(int idx, int lim) {
		for(Visit v: visits[idx]) {
			if(v.time > lim) return;
			if(count[v.pos] == 0) unique++;
			count[v.pos]++;
		}
	}
	
	public static void remove(int idx, int lim) {
		for(Visit v: visits[idx]) {
			if(v.time > lim) return;
			if(count[v.pos] == 1) unique--;
			count[v.pos]--;
			
		}
	}
	
	public static class Visit {
		int pos, time;
		Visit(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}
	}
}
