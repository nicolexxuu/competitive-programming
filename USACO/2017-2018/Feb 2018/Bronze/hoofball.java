import java.util.*;
import java.io.*;

public class hoofball {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[]cows = new int[N];
		for(int i = 0; i < N; i++) {
			cows[i] = sc.nextInt();
		}
		Arrays.sort(cows);
		// 6 	1 3 4 5 7 11
		//  	n y y y y n
		//      *d
		
		int result = 0;
		boolean[] noPass = new boolean[N];
		boolean[] passedTo = new boolean[N];
		for(int i = 0; i < N; i++) {
			boolean canPass1 = true;
			boolean canPass2 = true;
			int before = i - 1;
			int after = i + 1;
			if(i == 0 || (before - 1 >= 0 && cows[before] - cows[before - 1] <= cows[i] - cows[before]))canPass1 = false;
			if(i == N - 1 || (after + 1 < N && cows[after + 1] - cows[after] < cows[after] - cows[i])) canPass2 = false;	
			if(!canPass1 && !canPass2) {
				noPass[i] = true;
				result++;
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(noPass[i]) {
				passedTo[i] = true;
				int passTo = -1;
				int cow = i;
				if(cow != N - 1 && (i == 0 || cows[i + 1] - cows[i] < cows[i] - cows[i - 1])) passTo = 1;
				
				while(!passedTo[cow + passTo]) {
					cow = cow + passTo;
					passedTo[cow] = true;
					passTo = -1;
					if(cow != N - 1 && (cow == 0 || cows[cow + 1] - cows[cow] < cows[cow] - cows[cow - 1])) passTo = 1;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(!passedTo[i]) {
				result++;
				passedTo[i] = true;
				int passTo = -1;
				int cow = i;
				if(cow != N - 1 && (i == 0 || cows[i + 1] - cows[i] < cows[i] - cows[i - 1]))passTo = 1;
				
				while(!passedTo[cow + passTo]) {
					cow = cow + passTo;
					passedTo[cow] = true;
					passTo = -1;
					if(cow != N - 1 && (cow == 0 || cows[cow + 1] - cows[cow] < cows[cow] - cows[cow - 1])) passTo = 1;
				}
			}
		}
		
		System.out.println(result);
	}
}
