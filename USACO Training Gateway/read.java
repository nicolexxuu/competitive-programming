import java.io.*;
import java.util.*;

public class read {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); //reads one line
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int ppm = Integer.parseInt(st.nextToken());
			int maxTime = Integer.parseInt(st.nextToken());
			int restTime = Integer.parseInt(st.nextToken());
			
			int minutes = 0;
			int conMinutes = 0;
			int pages = 0;
			
			while(pages < N) {
				if(conMinutes == maxTime) {
					minutes += restTime;
					conMinutes = 0;
				}
				minutes++;
				conMinutes++;
				pages += ppm;
			}
			
			System.out.println(minutes);
		}
    }
    

}
