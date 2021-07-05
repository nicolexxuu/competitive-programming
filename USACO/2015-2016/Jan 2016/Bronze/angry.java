import java.util.*;
import java.io.*;


public class angry {
	
	static int[] haybales;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		haybales = new int[N];
		for(int i = 0; i < N; i++) {
			haybales[i] = in.nextInt();
		}
		
		Arrays.sort(haybales);
		int result = 0;
		for(int i = 0; i < N; i++) {
			boolean[] exploded = new boolean[N];
			explode(exploded, i, 0);
			
			int count = 0;
			for(Boolean b: exploded) {
				if(b) count++;
			}
			
			result = Math.max(result,  count);
		}
		
		System.out.println(result);

	}
	
	public static void explode(boolean[] exploded, int cow, int time) {
		int r = time + 1;
		int min = -1;
		int max = -1;
		System.out.println(time + " " + min);
		for(int i = 0; i < exploded.length; i++) {
			if(Math.abs(haybales[cow] - haybales[i]) <= r && !exploded[i]) {
				exploded[i] = true;
				if(i != cow) {
					if(min != -1)
					min = Math.min(i, min);
					else 
					min = i;
					
					if(max != -1)
					max = Math.max(i, max);
					else
					max = i;
				}
			}
		}
		
		System.out.println("starting cow: " + cow + " radius: " + r + " time: " + time + " min: " + min + " max: " + max);
		if(min != -1) explode(exploded, min, time + 1);
		if(max != -1) explode(exploded, max, time + 1);
	}
}


/* ANALYSIS
      v
3 4 5 6  8            13

R = 1



*/
