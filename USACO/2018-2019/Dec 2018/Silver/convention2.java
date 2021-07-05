import java.util.*;
import java.io.*;

public class convention2 {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader("convention2.in"));
		PrintWriter out = new PrintWriter(new File("convention2.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		Cow[] cows = new Cow[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			cows[i] = new Cow(a, b ,i);
		}
		Arrays.sort(cows);
		
		PriorityQueue<Cow> waiting = new PriorityQueue<Cow>(new CowComparator());
		int result = 0;
		int time = 0;
		int lastCowWaiting = 0;
		waiting.add(cows[0]);
		while(!waiting.isEmpty()) {
			Cow current = waiting.poll();
			if(current.s > time) {
				time = current.s + current.t;
			} else {
				result = Math.max(result, time - current.s);
				time += current.t;
			}
			
			for(int cow = lastCowWaiting + 1; cow < N; cow++) {
				if(cows[cow].s <= time || waiting.isEmpty()) {
					waiting.add(cows[cow]);
					lastCowWaiting = cow;
				}
				else break;
			}
		}

		out.println(result);
		System.out.println(result);
		out.close();
	}

	public static class Cow implements Comparable<Cow>{
		int seniority;
		int s;
		int t;
		
		Cow (int st, int time, int i){
			s = st;
			seniority = i;
			t = time;
		}
		public int compareTo(Cow other) {
			return this.s - other.s;
		}
	}
	
	public static class CowComparator implements Comparator<Cow>{

		public int compare(Cow o1, Cow o2) {
			if(o1.seniority == o2.seniority) return o1.s - o2.s;
			return o1.seniority - o2.seniority;
		}
		
	}
	/*
	 *  6 3 2
		1 1 10 14 4 3
	 * 
	 * 
	 * binary search and guess the maximum waiting times of cows
	 * and check if the number of buses is less than M
	 * 
	 */
}
