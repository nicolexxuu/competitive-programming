import java.util.*;
import java.io.*;

public class mowing {
	
	static int[] dx = {0, 1, 0, -1}; //N, E, S, W
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int H = 2001;
		HashMap<Integer, Integer> steps = new HashMap<>();
		      //key: x * H + y
		
		int result = -1;
		int x = 0;
		int y = 0;
		int time = 0;
		for(int i = 0; i < N; i++) {
			int d;
			char dir = in.next().charAt(0);
			if(dir == 'N') d = 0;
			else if(dir == 'E') d = 1;
			else if(dir == 'S') d = 2;
			else d = 3;
			
			int s = in.nextInt();
			
			for(int j = 0; j < s; j++) {
				time++;
				x += dx[d];
				y += dy[d];
				if(steps.containsKey(x * H + y)) {
					if(result == -1) result = Integer.MAX_VALUE;
					result = Math.min(result, time - steps.get(x * H + y));
				}
				
				steps.put(x * H + y, time);
			}
		}

		System.out.println(result);
	}
}

