import java.io.*;
import java.util.*;

public class icow {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		int [] songs = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			songs[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < T; i++) {
			int maxIndex = maxRating(songs);
			int rating = songs[maxIndex];
			System.out.println(maxIndex + 1);
			songs[maxIndex] = 0;
			int dist = rating / (N - 1);
			int R = rating % (N - 1);
			
			for(int j = 0; j < N; j++) {
				if(j != maxIndex) {
					songs[j] += dist;
					if(R > 0) {
						songs[j]++;
						R--;
					}
				}
			}
		}
    }
    
    public static int maxRating(int[] array) {
    	int max = 0;
    	for(int i = 0; i < array.length; i++) {
    		if(array[i] > array[max]) {
    			max = i;
    		}
    	}
    	return max;
    }
}
