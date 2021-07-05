import java.io.*;
import java.util.*;

public class cowtip {
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		boolean[][] cows = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char[] arr = st.nextToken().toCharArray();
			for(int j = 0; j < N; j++) {
				if(arr[j] == '0'){
					cows[i][j] = true;
				}
				
			}
		}
		
		
		int tips = 0;
		for(int r = N - 1; r >= 0; r--) {
			for(int c = N - 1; c >= 0; c--) {
				if(cows[r][c] == false) {
					tips++;
					for(int i = r; i >= 0; i--) {
						for(int j = c; j >= 0; j--) {
							if(cows[i][j]) {
								cows[i][j] = false;
							} else {
								cows[i][j] = true;
							}
						}
					}
				}
			}
		}
		
		System.out.println(tips);
    }

	
}
