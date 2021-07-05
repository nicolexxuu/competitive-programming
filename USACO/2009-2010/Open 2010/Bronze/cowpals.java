import java.io.*;
import java.util.*;

public class cowpals {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int S = Integer.parseInt(st.nextToken());
		
		for(int i = S;; i++) {
			if(divisorSum(divisorSum(i)) == i && divisorSum(i) != i) {
				System.out.println(i + " " + divisorSum(i));
				break;
			}
		}

    }
    
    public static int divisorSum(int S) {
    	int serialSum = 0;
		
		for(int i = 1; i < S; i++) {
			if(S % i == 0) {
				serialSum += i;
			}
		}
		
		return serialSum;
    }
}
