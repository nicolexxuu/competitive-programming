// Round Numbers

import java.util.*;
import java.io.*;

public class rndnum {
	static int M;
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()) - 1;
		String str = Integer.toBinaryString(S);
		int N = str.length();
		int[]A = new int[N];
		for(int i = 0; i < N; i++) A[i] = Integer.parseInt(str.substring(i,i+1));
		
		int F = Integer.parseInt(st.nextToken());
		str = Integer.toBinaryString(F);
		M = str.length();
		int[] B = new int[M];
		for(int i = 0; i < M; i++) B[i] = Integer.parseInt(str.substring(i,i+1));
		br.close();
		
		int result = 0;
		for(int o = 1; o <= M; o++) {
			for(int z = o; o+z <= M; z++) {
				//System.out.println("o: " + o + " z: " + z);
				if(o + z == M) result += valid(M-z-o+1, o-1, z, 0, B, M);
				else result += valid(M-z-o+1, o-1, z, 1, B, M);
			}
		}

		for(int o = 1; o <= N; o++) {
			for(int z = o; o+z <= N; z++) {
				if(o+z == N) result -= valid(N-z-o+1, o-1, z, 0, A, N);
				else result -= valid(N-z-o+1, o-1, z, 1, A, N);
			}
		}

		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(result);
		//out.println(result);
		//out.close();
	}
	
	public static int valid (int ind, int ol, int zl, int less, int[] a, int M) {
		int[][][][] states = new int[M][M+1][M+1][2];
		states[ind-1][ol][zl][less] = 1;
		
//		System.out.println("starting ind: " + ind + " one's left: " + ol + " zeroes left: " + zl + " is it already less? " + (less==1));
		
		for(int i = ind-1; i < M-1; i++) {
			for(int o = 0; o <= M; o++) {
				for(int z = 0; o+z <= M; z++) {
					for(int l = 0; l < 2; l++) {
						//System.out.println("i: " + i + " o: " + o + " z: " + z + " l: " + l + " curr val: " + states[i][o][z][l]);
						//1: less, 0: not less yet
						boolean both = l == 1;
						
						//try 1
						if(o > 0 && (both || a[i+1] == 1)) {
							//System.out.println("adding to one" + states[i][o][z][l]);
							states[i+1][o-1][z][l] += states[i][o][z][l];
						}
						
						//try 0
						if(z > 0) {
							int temp = 0;
							if(a[i] == 1 || both) temp = 1;
							//System.out.println("adding to zero" + states[i][o][z][l]);
							states[i+1][o][z-1][temp] += states[i][o][z][l];
						}
					}
				}
			}
		}
		//System.out.println("result: " + states[M-1][0][0][0] + " " + states[M-1][0][0][1]);
		return states[M-1][0][0][0] + states[M-1][0][0][1];
	}
}

