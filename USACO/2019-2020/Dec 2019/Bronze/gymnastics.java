import java.util.*;
import java.io.*;


public class gymnastics {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("gymnastics.in")));
		PrintWriter out = new PrintWriter(new File("gymnastics.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] places = new int[K][N];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for(int  j = 0; j < N; j++) {
				places[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		int result = 0;
		for(int c1 = 0; c1 < N; c1++) {
			for(int c2 = 0; c2 < N; c2++) {
				if(c1 == c2) continue;
				// c1 has to be ahead of c2
				boolean good = true;
				for(int i = 0; i < K; i++) {
					for(int j = 0; j < N; j++) {
						if(places[i][j] == c1) {
							break;
						} else if(places[i][j] == c2) {
							good = false;
							break;
						}
					}
				}
				if(good) result++;
			}
		}
		out.println(result);
		out.close();
		//System.out.println(result);
		

	}
}
