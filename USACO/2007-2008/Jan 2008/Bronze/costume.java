import java.io.*;
import java.util.*;

public class costume {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        
        int[] lengths = new int[N];
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	lengths[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(lengths);
        
        int result = 0;
        
        for(int l = 0; l < N; l++) {
        	for(int r = N - 1; r > l && lengths[r] - lengths[l] <= S; r--) {
        		result++;
        	}
        }
        
        System.out.println(result);
    }
}
