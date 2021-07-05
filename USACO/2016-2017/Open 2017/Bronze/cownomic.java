import java.io.*;
import java.util.*;

public class cownomics {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] cows = new char[N * 2][];
        
        for(int i = 0; i < N * 2; i++) {
        	st = new StringTokenizer(br.readLine());
        	cows[i] = st.nextToken().toCharArray();
        }
        
        int result = 0;
        for(int c = 0; c < M; c++) {
        	//A C G T
        	boolean[] genes1 = new boolean[4];
        	boolean[] genes2 = new boolean[4];
        	for(int r = 0; r < N; r++) {
        		if (cows[r][c] == 'A') genes1[0] = true;
        		else if (cows[r][c] == 'C') genes1[1] = true;
        		else if (cows[r][c] == 'G') genes1[2] = true;
        		else if (cows[r][c] == 'T') genes1[3] = true;
        	} 
        	
        	for(int r = N; r < N * 2; r++) {
        		if (cows[r][c] == 'A') genes2[0] = true;
        		else if (cows[r][c] == 'C') genes2[1] = true;
        		else if (cows[r][c] == 'G') genes2[2] = true;
        		else if (cows[r][c] == 'T') genes2[3] = true;
        	}
        	
        	boolean common = false;
        	for(int i = 0; i < 4; i++) {
        		if(genes1[i] && genes2[i]) common = true;
        	}
        	
        	if(common) result++;
        }
        
        System.out.println(result);
    }
}
