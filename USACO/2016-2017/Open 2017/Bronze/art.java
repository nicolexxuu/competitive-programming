import java.util.*;
import java.io.*;

public class art {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		boolean[] colors = new boolean[10];
		int N = in.nextInt();
		int[][] canvas = new int[N][N];
		for(int r = 0; r < N; r++) {
			String ln = in.next();
			for(int c = 0; c < ln.length(); c++) {
				int color = Integer.parseInt(ln.substring(c, c+1));
				colors[color] = true;
				canvas[r][c] = color;
			}
		}
		
		boolean[] colors2 = Arrays.copyOf(colors, 10);
		for(int color = 1; color <= 9; color++) {
			//System.out.println("color: " + color);
			if(!colors[color]) {
				continue;
				
			}
			int lr = N - 1; //upper left
			int lc = N - 1;
			int rr = 0; //lower right
			int rc = 0;
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if (canvas[r][c] != color) {
						continue;
					}
					if(c <= lc || r <= lr) {
						lc = Math.min(c, lc);
						lr = Math.min(r, lr);
					}
					
					if(c >= rc || r >= rr) {
						rc = Math.max(c, rc);
						rr = Math.max(r, rr);
					}
				}
			}

			//System.out.println(lr + " " + lc + "  " + rr + " " + rc);
			for(int r = lr; r <= rr; r++) {
				for(int c = lc; c <= rc; c++) {
					
					if(canvas[r][c] == color)continue;
					colors2[canvas[r][c]] = false;
				}
			}
			
		}
		
		int result = 0;
		for(int i = 1; i <= 9; i++) {
			if(colors2[i]) result++;
		}
		System.out.println(result);
	}
}
