import java.io.*;
import java.util.*;

public class cbarn {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] rooms = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			rooms[i] = Integer.parseInt(st.nextToken());
		}

		long minDist = Long.MAX_VALUE;
		for(int room = 0; room < N; room++) {
			int[] temp = new int[N];
			for(int i = 0; i < N; i++) {
				temp[i] = rooms[i];
			}
			long distance = 0;

			for(int furthestRoom = (room + N - 1) % N; furthestRoom != room; furthestRoom = (furthestRoom + N - 1) % N){
			    distance += findDistance(temp, room, furthestRoom) * temp[furthestRoom];
				temp[furthestRoom] = 0;
			}
			
			if(distance < minDist) {
				minDist = distance;
			}
			
		}
		
		System.out.println(minDist);

    }
    
    public static int findDistance(int[] array, int room, int froom) {
        if(room < froom){
            return froom - room;
        } else {
            return array.length - room + froom;
        }
    		
    }
}
