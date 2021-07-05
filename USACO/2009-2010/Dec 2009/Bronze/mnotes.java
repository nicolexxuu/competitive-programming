import java.io.*;
import java.util.*;

public class mnotes {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int [] notes= new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			notes[i] = Integer.parseInt(st.nextToken()) + notes[i-1];
		}
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(st.nextToken());
			System.out.println(noteSearch(notes, Integer.parseInt(st.nextToken())));
		}

    }
    
    public static int noteSearch(int[] notes, int q) {
    	int ret = Arrays.binarySearch(notes, q);
    	if(ret < 0) {
    		ret *= -1;
    		ret -= 1;
    	}
    	return ret;
    }


}
