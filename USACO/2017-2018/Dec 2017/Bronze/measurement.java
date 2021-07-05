import java.io.*;
import java.util.*;

public class measurement {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[] day = new int[N];
		int[] cow = new int[N];
		int[] change = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			day[i] = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			if(name.equals("Bessie")) {
				cow[i] = 1;
			} else if (name.equals("Mildred")) {
				cow[i] = 2;
			} else {
				cow[i] = 3;
			}
 			change[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] cows = new int[] {7, 7, 7};
		int changes = 0;
		for(int d = 1; d <= 100; d++) {
			int[] temp = new int[3];
			temp[0] = cows[0];
			temp[1] = cows[1];
			temp[2] = cows[2];
			for(int n = 0; n < N; n++) {
				if(day[n] == d) {
					temp[cows[n] - 1] += change[n];
				}
			}
			
			boolean[] tempstanding = standings(temp);
			boolean[] oldstanding = standings(cows);
			for(int i = 0; i < 3; i++) {
				if(tempstanding[i] != oldstanding[i]) {
					changes++;
					break;
				}
			}
			cows[0] = temp[0];
			cows[1] = temp[1];
			cows[2] = temp[2];
		}
		
		
		System.out.println(changes);
    }
    
    public static boolean[] standings(int[] arr) {
    	boolean[] standing = new boolean[3];
    	int max = 0;
    	for(int i = 0; i < arr.length; i++) {
    		if(arr[i] > max) {
    			max = arr[i];    		}
    	}
    	for(int i = 0; i < arr.length; i++) {
    		if(arr[i] == max) {
    			standing[i] = true;
    		}
    	}
    	
    	return standing;
    }

}



