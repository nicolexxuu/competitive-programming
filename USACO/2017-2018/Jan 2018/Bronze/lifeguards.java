import java.io.*;
import java.util.*;

public class lifeguards {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int [] guards = new int[1000];
		int [] starts = new int[N];
		int [] ends = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			starts[i] = start;
			int end = Integer.parseInt(st.nextToken());
			ends[i] = end;
			for(int j = start; j < end; j++) {
				guards[j]++;
			}
		}
		
		int maxTime = 0;
		
		for(int i = 0; i < N; i++) {
			maxTime = Math.max(maxTime, timeCovered(guards, starts[i], ends[i]));
		}
		
		System.out.println(maxTime);
    }
    
    public static int timeCovered(int[] guards, int start, int end) {
    	
    	int [] arr = guards.clone();
    	
    	for(int i = start; i < end; i++) {
    		arr[i]--;
    	}
    	
    	int time = 0;
    	
    	for(int i = 0; i < arr.length; i++) {
    		if(arr[i] > 0) {
    			time++;
    		}
    	}
    	
    	return time;
    }
    
    
}
