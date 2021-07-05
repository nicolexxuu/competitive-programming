import java.io.*;
import java.util.*;

public class secpas {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int count = 0;
		
		for(int i = 0; i*i <= N; i++) {
			for(int j = 0; j*j <= N; j++) {
				for(int k = 0; k*k <= N; k++) {
					if(Math.sqrt((double)(N - (i * i + j * j + k * k))) % 1 == 0)
							count++;
				}
			}
		}
		
		System.out.println(count);
		
    }
}
