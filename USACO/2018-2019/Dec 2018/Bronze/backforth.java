import java.io.*;
import java.util.*;

public class backforth {
	
	static HashSet<Integer> measures = new HashSet<>();
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("backforth.in"));
		int[] b1 = new int[11];
		int[] b2 = new int[11];
		for(int i = 0; i < 10; i++) {
			b1[i] = in.nextInt();
		}
		for(int i = 0; i < 10; i++) {
			b2[i] = in.nextInt();
		}
		
		in.close();
		day(Arrays.copyOf(b1,  11), Arrays.copyOf(b2, 11), 2);
		int result = measures.size();

		PrintWriter out = new PrintWriter(new File("backforth.out"));
		System.out.println(result);
		out.println(result);
		out.close();
	}
	
	public static void day(int[]b1, int[] b2, int d) {
		if(d == 6) {
			int sum = 0;
			for(int i = 0; i < 11; i++) {
				sum += b1[i];
			}
			measures.add(sum);
			
		} else {
			if(d % 2 == 0) { //tues, thurs (first to second)
				for(int i = 0; i < 11; i++) {
					if(b1[i] != 0) {
						int bucket = b1[i];
						b1[i] = 0;
						for(int j = 0; j < 11; j++) {
							if(b2[j] == 0) {
								b2[j] = bucket;
								day(Arrays.copyOf(b1, 11), Arrays.copyOf(b2,  11), d + 1);
								b2[j] = 0;
							}
						}
						b1[i] = bucket;
						
					}
				}
			} else { //weds, fri (second to first)
				for(int i = 0; i < 11; i++) {	
					if(b2[i] != 0) {
						int bucket = b2[i];
						b2[i] = 0;
						for(int j = 0; j < 11; j++) {
							if(b1[j] == 0) {
								b1[j] = bucket;
								day(Arrays.copyOf(b1, 11), Arrays.copyOf(b2,  11), d + 1);
								b1[j] = 0;
							}
						}
						b2[i] = bucket;
						
					}
					
				}
			}

		}
	}
	
	public static void printArr(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}
}
