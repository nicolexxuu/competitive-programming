import java.util.*;
import java.io.*;

public class cowqueue {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		Cow[] cows = new Cow[N];
		for(int i = 0; i < N; i++) {
			cows[i] = new Cow(in.nextInt(), in.nextInt());
		}
		Arrays.sort(cows);
		
		int time = 0;
		for(int cow = 0; cow < N; cow++) {
			if(time <= cows[cow].arrival) {
				time = cows[cow].arrival;
			}
			time += cows[cow].time;
		}
		System.out.println(time);
	}
	
	public static class Cow implements Comparable<Cow>{
		int arrival;
		int time;
		Cow(int a, int t){
			arrival = a;
			time = t;
		}
		
		public int compareTo(Cow other) {
			return this.arrival - other.arrival;
		}
	}
}
