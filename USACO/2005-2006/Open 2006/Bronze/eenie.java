import java.io.*;
import java.util.*;

public class eenie {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		boolean[] cows = new boolean[N];
		int[] seq = new int[L];
		
		for(int i = 0; i < cows.length; i++) {
			cows[i] = true;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < L; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		int cow = -1;
		int i = 0;
		boolean gameInProgress = true;
		while(gameInProgress) {
			for(int j = 0; j < seq[i]; j++) {
				cow = (cow + 1) % N;
				while(!cows[cow]) {
					cow = (cow + 1) % N;
				}
			}
			cows[cow] = false;
			if(lastCowStanding(cows) != -1) {
			System.out.println(lastCowStanding(cows) + 1);
			gameInProgress = false;
			}
			i = (i + 1) % L;
		}
    }
    
    
    public static int lastCowStanding(boolean[] cows) {
    	int cow = -1;
    	for(int i = 0; i < cows.length; i++) {
    		if(cows[i]) {
    			if(cow == -1) {
    				cow = i;
    			} else {
    				return -1;
    			}
    		}
    	}
    	
    	return cow;
    }
    
}
