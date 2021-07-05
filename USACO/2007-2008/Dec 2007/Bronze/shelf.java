import java.io.*;
import java.util.*;

public class shelf {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int[] cows = new int[N];
		for(int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine());
			cows[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(cows);
		
		int count = 0;
		int total = 0;
		for(int i = N - 1; i >= 0; i--) {
			count++;
			total += cows[i];
			if(total >= B) break;
		}
		
		System.out.println(count);
    }
}
