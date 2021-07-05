import java.util.*;
import java.io.*;

public class pktri1 {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int S = in.nextInt();
		
		int[][] nums = new int[N][];
		int index = S;
		for(int c = 0; c < N; c++) {
			nums[c] = new int[c + 1];
			for(int r = 0; r < c + 1; r++) {
				nums[c][r] = index;
				index++;
				if(index == 10) index = 1;
			}
		}

		for(int r = 0; r < N; r++) {
			for(int c = 0; c < r; c++) {
				System.out.print("  ");
			}
			
			for(int c = r; c < N; c++) {
				System.out.print(nums[c][r]);
				if(c != N - 1) System.out.print(" ");
			}
			System.out.println();
		}
	}
}
