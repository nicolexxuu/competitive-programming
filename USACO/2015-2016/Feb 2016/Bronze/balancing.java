import java.io.*;
import java.util.*;

public class balancing {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] x = new int[N];
		int[] y = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		int minMax = Integer.MAX_VALUE;
		
		for(int xi = 0; xi < N; xi++) {
			for(int yi = 0; yi < N; yi++) {
				int r = x[xi] + 1;
				int c = y[yi] + 1;
				int[] counts = new int[4];
				for(int i = 0; i < N; i++) {
					if(x[i] < r && y[i] < c) {
						counts[0]++;
					} else if(x[i] < r && y[i] > c) {
						counts[1]++;
					} else if(x[i] > r && y[i] < c) {
						counts[2]++;
					} else if(x[i] > r && y[i] > c) {
						counts[3]++;
					}
				}
				int max = Math.max(counts[0], Math.max(counts[1], Math.max(counts[2], counts[3])));
				if(max < minMax) {
					minMax = max;
				}
			}
		}
        System.out.println(minMax);
			
    }
    
   


}
