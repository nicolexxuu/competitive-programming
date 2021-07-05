import java.io.*;
import java.util.*;

public class shuffle {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		String [] cows = new String[N + 1];
		int [] seq = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++){
			seq[i] = Integer.parseInt(st.nextToken()); 
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			cows[i] = st.nextToken();
		}
		
		for(int i = 1; i <= 3; i++) {
			String[] temp = new String[N + 1];
			for(int j = 1; j <= N; j++) {
				temp[j] = cows[j];
			}
			for(int j = 1; j <= N; j++) {
				cows[j] = temp[seq[j]];
			}
			
		}
		
		for(String s: cows) {
			System.out.println(s);
		}
				
    }

}
