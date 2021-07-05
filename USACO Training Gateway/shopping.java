// Shopping Offers

import java.util.*;
import java.io.*;

public class shopping {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[][][][][] dist = new int[6][6][6][6][6];
		int[] cartAmts = new int[5];
		int[] cartIds = new int[5];
		
		int s = Integer.parseInt(st.nextToken());
		ArrayList<State> options = new ArrayList<>();
		for(int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			options.add(new State(n));
			for(int j = 0; j < n; j++) {
				options.get(i).id[j] = Integer.parseInt(st.nextToken());
				options.get(i).amt[j] = Integer.parseInt(st.nextToken());
			}
			options.get(i).cost = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		for(int i = s; i < s+b; i++) {
			st = new StringTokenizer(br.readLine());
			options.add(new State(1));
			options.get(i).id[0] = Integer.parseInt(st.nextToken());
			cartIds[i-s] = options.get(i).id[0];
			cartAmts[i-s] = Integer.parseInt(st.nextToken());
			options.get(i).amt[0] = 1;
			options.get(i).cost = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		PriorityQueue<State> toVisit = new PriorityQueue<State>();
		toVisit.add(new State(5));
		
		while(!toVisit.isEmpty()) {
			State curr = toVisit.remove();
			int[] amt = curr.amt;
			int cost = curr.cost;
			if(dist[amt[0]][amt[1]][amt[2]][amt[3]][amt[4]] != 0) continue;
			dist[amt[0]][amt[1]][amt[2]][amt[3]][amt[4]] = cost;
			
			for(State o: options) {
				int[] updated = Arrays.copyOf(amt, amt.length);
				int i = 0;
				while(i < o.n) {
					int loc = findLoc(cartIds, o.id[i]);
					if(loc == -1 || o.amt[i] + updated[loc] > cartAmts[loc]) break;
					updated[loc] += o.amt[i];
					i++;
				}

				if(i == o.n && dist[updated[0]][updated[1]][updated[2]][updated[3]][updated[4]] == 0) {
					State temp = new State(5);
					temp.cost = o.cost + cost;
					temp.amt = updated;
					toVisit.add(temp);
				}
				
			}
		}
		
		int result = dist[cartAmts[0]][cartAmts[1]][cartAmts[2]][cartAmts[3]][cartAmts[4]];
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
	public static int findLoc(int[] a, int val) {
		for(int i = 0; i < a.length; i++) if(a[i] == val) return i;
		return -1;
	}

	public static class State implements Comparable <State> {
		int n;
		int[] id, amt;
		int cost;
		
		State(int n) {
			this.n = n;
			id = new int[5];
			amt = new int[5];
		}
		
		public int compareTo (State other) {
			return cost - other.cost;
		}
	}
}
