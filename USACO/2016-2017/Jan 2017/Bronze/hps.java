import java.io.*;
import java.util.*;

public class hps {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int [][] games = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			games[i][0] = Integer.parseInt(st.nextToken());
			games[i][1] = Integer.parseInt(st.nextToken());
		}
		char[][] gestures = new char[][]{{' ', 'S' , 'H', 'P'},
		{' ', 'S' , 'P', 'H'},
		{' ', 'H' , 'S', 'P'},
		{' ', 'H' , 'P', 'S'},
		{' ', 'P' , 'H', 'S'},
		{' ', 'P' , 'S', 'H'}};
		
		int maxWon = 0;
		for(int i = 0; i < 6; i++) {
			int count = 0;
			for(int j = 0; j < N; j++) {
				if(gestures[i][games[j][0]] == 'S' && gestures[i][games[j][1]] == 'P') {
					count++;
				} else if(gestures[i][games[j][0]] == 'P' && gestures[i][games[j][1]] == 'H') {
					count++;
				} else if(gestures[i][games[j][0]] == 'H' && gestures[i][games[j][1]] == 'S') {
					count++;
				} else {}
			}
			if(count > maxWon) maxWon = count;
		}
		
		System.out.println(maxWon);
    }
}
