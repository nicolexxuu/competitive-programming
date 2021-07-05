import java.util.*;
import java.io.*;

public class trapped {
	static Haybale [] haybales;
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		haybales = new Haybale[N];
		
		for(int i = 0; i < N; i++) {
			haybales[i] = new Haybale(in);
		}
		
		Arrays.sort(haybales);
		int result = 0;
		
		for(int right = 1; right < N; right++) {
			int l = right - 1;
			int r = right;
			
			while(l >= 0 && r < N) {
				int dist = haybales[r].p - haybales[l].p;
				if(dist <= haybales[r].s && dist <= haybales[l].s) break;
				if(dist > haybales[l].s) {
					l--;
				} 
				if(dist > haybales[r].s) {
					r++;
				}
			}
			
			if(l != -1 && r != N) {
				result += haybales[right].p - haybales[right - 1].p;
			}
			
		}
		System.out.println(result);
	}
	
	public static class Haybale implements Comparable<Haybale>{
		int p;
		int s;
		Haybale(Scanner in){
			s = in.nextInt();
			p = in.nextInt();
		}
		
		public int compareTo(Haybale other) {
			return p - other.p;
		}
	}
}


/* ANALYSIS
 *        7              7               
1  2  3  4  5  6  7  8  9  10 11 12 14 15 16 17 18 19 20
8        1           8                 7              4



*/
