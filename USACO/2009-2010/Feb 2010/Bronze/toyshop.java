import java.io.*;
import java.util.*;

public class toyshop {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] maxHFM = new int[3];
		int[] maxPrice = new int[3];
		
		double[][] stuff = new double[N + 1][2];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int joy = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			stuff[i][0] = (double)joy/price;
			stuff[i][1] = price;
		}
		
		for(int i = 0; i <= N; i++) {
			if(stuff[i][0] > stuff[maxHFM[0]][0]) {
				maxHFM[2] = maxHFM[1];
				maxHFM[1] = maxHFM[0];
				maxHFM[0] = i;
			}
			else if(stuff[i][0] > stuff[maxHFM[1]][0]) {
				maxHFM[2] = maxHFM[1];
				maxHFM[1] = i;
			}
			else if(stuff[i][0] > stuff[maxHFM[2]][0]) {
				maxHFM[2] = i;
			}
		}
		
		int sum = 0;
		for(int i = 0; i < 3; i++) {
			sum += stuff[maxHFM[i]][1];
		}
		
		System.out.println(sum);
		for(int i = 0; i < 3; i++) {
			System.out.println(maxHFM[i]);
		}
		
    }

}

