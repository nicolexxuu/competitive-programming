
// Privileged Cows

import java.util.*;
import java.io.*;

public class privc {
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int [] cows = new int[N];
		int [][] range = new int[4][2];
		int [] count = new int[4];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = Integer.parseInt(st.nextToken());
			count[cows[i]]++;
		}
		
		for(int i = 1; i <= 3; i++) {
			range[i][0] = range[i-1][1];
			range[i][1] = range[i][0] + count[i];
		}
		br.close();
		
		int swaps = 0;
		// first, swap cows that can be fixed w/ one move
		for(int cow = 1; cow <= 2; cow++) {
			for(int cow2 = cow + 1; cow2 <= 3; cow2++) {
				for(int i = range[cow][0]; i < range[cow][1]; i++) {
					for(int j = range[cow2][0]; j < range[cow2][1]; j++) {
						if(cows[i] == cow2 && cows[j] == cow) {
							swaps++;
							cows[i] = cow;
							cows[j] = cow2;
						}
					}
				}
			}
		}

		// then, just swap everything else
		
		int t = 0;
		for(int cow = 1; cow <= 3; cow++) 
			for(int i = range[cow][0]; i < range[cow][1]; i++) 
				if(cows[i] != cow) t++;
		
		
		swaps += ((t/3) * 2);
		
//		for(int cow = 1; cow <= 2; cow++) {
//			for(int i = range[cow][0]; i < range[cow][1]; i++) {
//				if(cows[i] == cow) continue;
//				for(int j = range[cow][1]; j < N; j++) {
//					if(cows[j] == cow) {
//						swaps++;
//						int temp = cows[i];
//						cows[i] = cows[j];
//						cows[j] = temp;
//						break;
//					}
//				}
//			}
//		}
		//for(int i: cows) System.out.println(i);
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(swaps);
		//out.println(result);
		//out.close();
	}
}

