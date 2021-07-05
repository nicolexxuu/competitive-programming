import java.io.*;
import java.util.*;

public class flowers {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	int F = Integer.parseInt(st.nextToken());
	int K = Integer.parseInt(st.nextToken());
	boolean[] flowers = new boolean[F + 1];

	for(int i = 0; i < K; i++) {
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int interval = Integer.parseInt(st.nextToken());
		for(int flower = start; flower <= F; flower += interval) {
			flowers[flower] = true;
		}
	}

	int count = 0;
	for(boolean b: flowers) {
		if(!b) count++;
	}
	System.out.println(count - 1);
		
    }


}
