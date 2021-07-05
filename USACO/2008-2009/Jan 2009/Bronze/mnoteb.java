import java.io.*;
import java.util.*;

public class mnoteb {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		int[] beats = new int[N + 1];
		beats[0] = -1;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			beats[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			beats[i] += beats[i - 1];
		}
		
		for(int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			for(int note = N; note > 0; note--) {
				if(time <= beats[note] && time > beats[note - 1]) {
					System.out.println(note);
					break;
				}
			}
		}
    }


}
