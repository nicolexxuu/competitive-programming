import java.io.*;
import java.util.*;

public class timecards {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Nlines = Integer.parseInt(st.nextToken());
		
		int[] startHrs = new int[N];
		int[] startMins = new int[N];
		int[] cows = new int[N];
		
		for(int i = 0; i < Nlines; i++) {
			st = new StringTokenizer(br.readLine());
			int cow = Integer.parseInt(st.nextToken());
			String str = st.nextToken();
			if(str.equals("START")) {
				startHrs[cow - 1] = Integer.parseInt(st.nextToken());
				startMins[cow - 1] = Integer.parseInt(st.nextToken());
			} else {
				int hr = Integer.parseInt(st.nextToken());
				int min = Integer.parseInt(st.nextToken());
				cows[cow - 1] += toMin(hr) + min - toMin(startHrs[cow - 1]) - startMins[cow - 1];
				startHrs[cow - 1] = 0;
				startMins[cow - 1] = 0;
			}
		}
		
		for(int i = 0; i < cows.length; i++) {
			int[] time = toHrs(cows[i]);
			System.out.println(time[0] + " " + time[1]);
		}
    }
    
    public static int toMin(int time) {
    	return time * 60;
    }
    
    public static int[] toHrs(int mins) {
    	int[] time = new int[2];
    	time[0] = mins / 60;
    	time[1] = mins % 60;
    	return time;
    }
}
