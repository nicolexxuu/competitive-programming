import java.io.*;
import java.util.*;

public class bgrass {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		boolean[][] grass = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			char[] temp = st.nextToken().toCharArray();
			for(int j = 0; j < temp.length; j++) {
				if(temp[j] == '#') {
					grass[i][j] = true;
				}
			}
		}
		
		int clumps = 0;
		for(int r = 0; r < grass.length; r++) {
			for(int c = 0; c < grass[r].length; c++) {
				if(grass[r][c]) {
					if(c != grass[r].length - 1) {
						if(grass[r][c + 1]) {
							grass[r][c + 1] = false;
						}
					}
					
					if(r != grass.length - 1) {
						if(grass[r + 1][c]) {
							grass[r + 1][c] = false;
						}
					}
					
					clumps++;
					grass[r][c] = false;
				}
			}
		}
		
		System.out.println(clumps);
    }
}
