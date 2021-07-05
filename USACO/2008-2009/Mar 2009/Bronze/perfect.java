import java.util.*;
import java.io.*;

public class perfect {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int [][] cows = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int  j = 0; j < N; j++) {
				cows[i][j] = in.nextInt();
			}
		}
		
		for(int i = 0; i < N ; i++) {
			Arrays.sort(cows[i]);
		}
		
		int[] meds = new int[N];
		for(int i = 0 ; i < N; i++) {
			meds[i] = cows[i][N / 2];
		}
		
		Arrays.sort(meds);

		System.out.println(meds[N / 2]);
	}
}
