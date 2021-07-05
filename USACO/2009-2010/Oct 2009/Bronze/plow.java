import java.util.*;
import java.io.*;

public class plow {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int I = Integer.parseInt(st.nextToken());
		boolean[][] plowed = new boolean[X + 1][Y + 1];
		for(int i = 0; i < I; i++) {
			st = new StringTokenizer(br.readLine());
			int Xll = Integer.parseInt(st.nextToken());
			int Yll = Integer.parseInt(st.nextToken());
			int Xur = Integer.parseInt(st.nextToken());
			int Yur = Integer.parseInt(st.nextToken());
			for(int x = Xll; x <= Xur; x++) {
				for(int y = Yll; y <= Yur; y++) {
					plowed[x][y] = true;
				}
			}
		}
		
		br.close();

		int result = 0;
		for(int i = 1; i <= X; i++) {
			for(int j = 1; j <= Y; j++) {
				if(plowed[i][j]) result++;
			}
		}
		System.out.println(result);
	}
}
