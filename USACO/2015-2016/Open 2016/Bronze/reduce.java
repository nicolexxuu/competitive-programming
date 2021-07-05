import java.io.*;
import java.util.*;

public class reduce {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;
		int[] indexes = new int[4];

		int[][] cows = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i][0] = Integer.parseInt(st.nextToken());
			cows[i][1] = Integer.parseInt(st.nextToken());
			if(cows[i][0] < minX) {
				minX = cows[i][0];
				indexes[0] = i;
			}
			if(cows[i][0] > maxX) {
				maxX = cows[i][0];
				indexes[0] = i;
			}
			if(cows[i][1] < minY) {
				minY = cows[i][0];
				indexes[0] = i;
			}
			if(cows[i][1] > maxY) {
				maxY = cows[i][0];
				indexes[0] = i;
			}
		}
		
		int[] count = new int[4];
		for(int i = 0; i < N; i++) {
			if(cows[i][0] == minX) count[0]++;
			if(cows[i][0] == maxX) count[1]++;
			if(cows[i][1] == minY) count[2]++;
			if(cows[i][1] == maxY) count[3]++;
		}
		
		int length = (maxX - minX + maxY - minY) * 2;
		for(int i = 0; i < 4; i++) {
			int[] temps = new int[4];
			if(count[i] == 1) {
				for(int j = 0; j < N; j++) {
					if(j != indexes[i]) {
						if(cows[i][0] < minX) temps[0] = cows[i][0];
						if(cows[i][0] > maxX) temps[1] = cows[i][0];
						if(cows[i][1] < minY) temps[2] = cows[i][1];
						if(cows[i][1] > maxY) temps[3] = cows[i][1];
					}
				}
			}
			if((temps[0] - temps[1] + temps[2] - temps[3]) * 2 < length) length = (temps[0] - temps[1] + temps[2] - temps[3]) * 2;
		}
		
		System.out.println(length);
    }


}
