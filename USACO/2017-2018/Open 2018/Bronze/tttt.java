import java.io.*;
import java.util.*;

public class tttt {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] ttt = new char[3][3];
        for(int i = 0; i < 3; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	ttt[i] = st.nextToken().toCharArray();
        }
        
        int idvcount = 0;
        int teamcount = 0;
   
        for(char c = 'A'; c <= 'Z'; c++) {
        	if(oneCow(ttt, c)) idvcount++;
        	for(char c2 = (char)(c + 1); c2 <= 'Z'; c2++) {
        		if(twoCows(ttt, c, c2)) teamcount++;
        	}
        	
        }

        System.out.println(idvcount);
        System.out.println(teamcount);
    }
    
    
    public static boolean oneCow(char[][] ttt, char c) {
    	for(int n = 0; n < 3; n++) {
    		if(ttt[0][n] == c && ttt[1][n] == c && ttt[2][n] == c) return true;
    		if(ttt[n][0] == c && ttt[n][1] == c && ttt[n][2] == c) return true;
    	}
    	
    	if(ttt[0][0] == c && ttt[1][1] == c && ttt[2][2] == c) return true;
    	if(ttt[2][0] == c && ttt[1][1] == c && ttt[0][2] == c) return true;
    	return false;
    }
    
    public static boolean twoTeams(char c1, char c2, char a, char b, char c) {
    	if((a != c1 && a != c2) || (b != c1 && b != c2) || (c != c1 || c != c2)) {
    		return false;
    	}
    	
    	if((c1 != a && c1 != b && c1 != c) ||  (c2 != a || c2 != b || c2 != c)) {
    		return false;
    	}
    	return true;
    }
    
    public static boolean twoCows(char[][] ttt, char c1, char c2) {
    	for(int n = 0; n < 3; n++) {
    		if(twoTeams(c1, c2, ttt[0][n], ttt[1][n], ttt[2][n])) return true;
    		if(twoTeams(c1, c2, ttt[n][0], ttt[n][1], ttt[n][2])) return true;
    	}
    }
}
