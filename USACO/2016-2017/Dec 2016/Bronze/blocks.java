import java.io.*;
import java.util.*;

public class blocks {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] chars = new int[26];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String w1 = st.nextToken();
			String w2 = st.nextToken();
			for(char c = 'a'; c <= 'z'; c++) {
				chars[c - 'a'] += Math.max(freq(w1, c), freq(w2, c));
			}
		}
		
		for(int c: chars) {
			System.out.println(c);
		}
    }
    
    public static int freq(String word, char c) {
    	int count = 0;
    	for(int i = 0; i < word.length(); i++) {
    		if(word.charAt(i) == c) {
    			count++;
    		}
    	}
    	return count;
    }
}
