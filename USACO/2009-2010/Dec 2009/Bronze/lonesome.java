import java.io.*;
import java.util.*;

public class lonesome {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long[][] cows = new long[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i][0] = Long.parseLong(st.nextToken());
			cows[i][1] = Long.parseLong(st.nextToken());
		}
		
		int[] maxCows = new int[2];
		double maxDistance = -1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(distance(cows[i][0], cows[i][1], cows[j][0], cows[j][1]) > maxDistance) {
					maxDistance = distance(cows[i][0], cows[i][1], cows[j][0], cows[j][1]);
					maxCows[0] = i + 1;
					maxCows[1] = j + 1;
				}
			}
		}
		
		Arrays.sort(maxCows);
		System.out.println(maxCows[0] + " " + maxCows[1]);
    }
    
    public static double distance(long x1, long y1, long x2, long y2) {
    	return Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));
    }
}
