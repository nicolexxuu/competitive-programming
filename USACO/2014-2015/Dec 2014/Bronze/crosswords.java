import java.io.*;
import java.util.*;

public class crosswords {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] grid = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char[] line = st.nextToken().toCharArray();
			for(int a = 0; a < line.length; a++) {
				if(line[a] == '#') {
					grid[i][a] = 1;
				}
			}
		}
		
		int count = 0;
		ArrayList<Integer> coords = new ArrayList<>();
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(grid[r][c] == 0) {
					if(c == 0 || grid[r][c - 1] == 1) {
						if(c + 2 < M) {
							if(grid[r][c + 1] == 0 && grid[r][c + 2] == 0) {
								coords.add(r + 1);
								coords.add(c + 1);
								count++;
								continue;
							}
						} 
					}
					
					if(r == 0 || grid[r - 1][c] == 1) {
						if(r + 2 < N) {
							if(grid[r + 1][c] == 0 && grid[r + 2][c] == 0) {
								coords.add(r + 1);
								coords.add(c + 1);
								count++;
								continue;
							}
						} 
					}
				}
			}
		}
		
		System.out.println(count);
		for(int i = 0; i < coords.size(); i += 2) {
			System.out.println(coords.get(i) + " " +  coords.get(i + 1));
		}
		
    }
}
