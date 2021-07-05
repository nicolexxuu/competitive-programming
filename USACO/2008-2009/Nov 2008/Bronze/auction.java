import java.util.*;
import java.io.*;

public class auction {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		int[] farmers = new int[M];
		for(int i = 0; i < M; i++) {
			farmers[i] = in.nextInt();
		}
		Arrays.sort(farmers);

		int minPrice = farmers[M - 1];
		int maxRev = farmers[0];
		
		for(int i = 0; i < M; i++) {
			int price = farmers[i];
			int rev = 0;
			for(int farmer = M - 1; farmer >= 0 && M - farmer <= N; farmer--) {
				if(farmers[farmer] >= price) {
					rev += price;
				} else {
					break;
				}
			}
			
			if(rev > maxRev){
			    maxRev = rev;
			    minPrice = price;
			}
		}
		
		System.out.println(minPrice + " " + maxRev);
	}
}
