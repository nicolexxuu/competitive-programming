

import java.util.*;
import java.io.*;

public class perimeter {
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
		PrintWriter out = new PrintWriter(new File("perimeter.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int [][] grid = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			char[] row = st.nextToken().toCharArray();
			for(int c = 0; c < N; c++) {
				if(row[c] == '#') {
					grid[r][c] = 1;
				}
			}
		}
		
		br.close();
		
		int maxArea = 0;
		int perimeter = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(grid[r][c] != 0 && grid[r][c] != 2) { 
					// if grid space if filled with ice cream and is not visited
					int area = 1;
					int perim = 0;
					LinkedList<Integer> list = new LinkedList<Integer>();
					list.add( r*(N)+c );
					grid[r][c] = 2;
					
					while( !list.isEmpty() ) {
						int curr = list.removeFirst();   // take the first from the front
						
						int r0 = curr/(N);
						int c0 = curr%(N);
//						
						for(int k=0; k<4; k++) {
							int r1 = r0 + dr[k];
							int c1 = c0 + dc[k];
							if( r1>=0 && r1<N && c1>=0 && c1<N && grid[r1][c1]==1 ) {
								grid[r1][c1] = 2;  // mark this square as "visited"
								list.add( r1*(N)+c1 );
								area++;
							} else {
								perim++;
							}
						}
					}
					

					if(area >= maxArea) {
						if(area == maxArea) {
							if(perimeter != 0) perimeter = Math.min(perimeter, perim);
							else perimeter = perim;
						}
						maxArea = area;
					}
				}
			}
		}
		
		out.println(maxArea + " " + perimeter);
		out.close();
	}
}
