import java.io.*;
import java.util.*;

public class taming {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int [] log = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			log[i] = Integer.parseInt(st.nextToken());
		}
		
		int minBreakouts = 0;
		int maxBreakouts = 0;
		
		log[0] = 0;
		
		for(int i = N - 2; i >= 0; i--) {
			int value = -1;
			if(log[i + 1] > 0) {
				value = log[i + 1] - 1;
			}
			
			if(log[i] != -1 && log[i] != value) {
				System.out.println(1);
			} else {
				log[i] = value;
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(log[i] == 0) {
				maxBreakouts++;
				minBreakouts++;
			} if(log[i] == -1) {
				maxBreakouts++;
			}
		}
		
		System.out.println(minBreakouts + " " + maxBreakouts);
    }
}
