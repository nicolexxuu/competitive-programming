import java.io.*;
import java.util.*;

public class goodgrs {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int NR = Integer.parseInt(st.nextToken());
		int NC = Integer.parseInt(st.nextToken());
		
		int[][] grass = new int[NR][NC];
		
		for(int i = 0; i < NR; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < NC; j++) {
				grass[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxValue = 0;
		int maxR = 0;
		int maxC = 0;
		
		for(int i = 0; i < NR - 2; i++) {
			for(int j = 0; j < NC - 2; j++) {
				int temp = 0;
				for(int r = i; r < i + 3; r++) {
					for(int c = j; c < j + 3; c++) {
						temp += grass[r][c];
					}
				}
				if(temp > maxValue) {
					maxValue = temp;
					maxR = i;
					maxC = j;
				}
				
			}
		}
		
		System.out.println(maxValue);
		System.out.println(maxR + " " + maxC);

		
    }
}
