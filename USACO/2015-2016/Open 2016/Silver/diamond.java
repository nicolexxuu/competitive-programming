import java.io.*;
import java.util.*;

public class diamond {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] diamonds = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			diamonds[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(diamonds);

		int maxDiamonds = 0;
		
		for(int min = 0; min < N; min++) {
			int count = 0;
			for(int i = 0; i < N; i++) {
				if(diamonds[i] >= diamonds[min] && diamonds[i] - diamonds[min] <= K)
					count++;
			}
			if(count > maxDiamonds) maxDiamonds = count;
		}
		
		System.out.println(maxDiamonds);
    }
}
