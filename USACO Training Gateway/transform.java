import java.util.*;
import java.io.*;

public class transform {
	
	static int[][] before;
	static int[][] after;
	static int N;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		before = new int[N][N];
		after = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			char[] line = in.next().toCharArray();
			for(int j = 0; j < N; j++) {
				if(line[j] == '@') before[i][j] = 1;
			}
			//System.out.println();
		}
		
		for(int i = 0; i < N; i++) {
			char[] line = in.next().toCharArray();
			for(int j = 0; j < N; j++) {
				if(line[j] == '@') after[i][j] = 1;
			}
		}
		
		int result = 7;
		if(equal()) result = 6;
		
		for(int i = 1; i <= 6; i++) {
			//System.out.println("i = " + i);
			if(i == 5) continue;
			
			if(i <= 4) {
				rotate();
			//System.out.println("after rotation: ");
			//printArr(before);
				if(equal()) {
					result = i;
					break;
				}
			}
			
			reflect(before);
			if(equal()) {
				if(i < 4) result = 5;
				else result = 4;
				break;
			}
			reflect(before);
		}
		
		System.out.println(result);
	}
	
	public static void rotate() {
		int[][] n = new int[N][N];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				n[c][N - 1 - r] = before[r][c];
			}
		}
		before = n;
		//printArr(before);
	}
	
	public static void reflect(int[][] a) {
		int[][] n = new int[N][N];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				n[r][N - 1 - c] = a[r][c];
			}
		}
		before = n;
		//printArr(before);
	}
	
	public static boolean equal() {
		for(int i = 0; i < before.length; i++) {
			for(int j = 0; j < before[i].length; j++) {
				if(before[i][j] != after[i][j]) return false;
			}
		}
		
		return true;
	}
	
	public static void printArr(int [][] a ) {
		for(int i = 0;  i < a.length; i++) {
			for(int j = 0; j < a.length; j++) {
				System.out.print(a[i][j]);
			}
			System.out.println();
		}
	}
}
