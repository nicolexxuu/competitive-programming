import java.io.*;
import java.util.*;

public class haybales {
	public static void main(String[] args) throws IOException{
	BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        
        int[] haybales = new int[N];
        for(int i = 0; i < N; i++) {
        	haybales[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 0; i < Q; i++) {
        	st = new StringTokenizer(br.readLine());
        	int L = Integer.parseInt(st.nextToken());
        	int R = Integer.parseInt(st.nextToken());
        	int loc2 = upperBound(haybales, R);
        	int loc1 = lowerBound(haybales, L);
        	
        	if(loc1<0 || loc2<0) {
        		System.out.println(0);
        	} else {
        		System.out.println(loc2 - loc1 - 1);
        	}
        }

        Arrays.parallelSort(haybales);
        pw.close();

	}
	
	public static int lowerBound(int[] A, int target) {
		
		if(A[0] >= target) return 0;
		if(A[A.length - 1] < target) return -1;
		int low = 0;
		int high = A.length - 1;
		while(low < high) {
			int mid = (low + high) / 2;
			if(A[mid] >= target) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		
		return low;
	}
	public static int upperBound(int[]A, int target) {
		int low = 0;
		int high = A.length - 1;
		while(low < high) {
			int mid = (low + high) / 2;
			if(A[mid] >= target) {
				high = mid - 1;
			} else {
				low = mid;
			}
		}
		
		return high;
	}
	
}
