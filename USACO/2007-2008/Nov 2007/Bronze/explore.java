import java.io.*;
import java.util.*;
public class explore {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Cow[] cows = new Cow[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i + 1);
		}
		
		Arrays.sort(cows, new comp());
		
		Cow max = new Cow(0, 0, 0);
		for(int i = 0; i < K; i++) {
			if(cows[i].v2 > max.v2){
			    max = cows[i];
			}
		}
		
		System.out.println(max.index);
    }
    
    public static class Cow {
    	int v1, v2, index;
    	
    	Cow (int l, int m, int i){
    		v1 = l;
    		v2 = m;
    		index = i;
    	}
    }
    
    static class comp implements Comparator<Cow>{
    	public int compare(Cow a, Cow b) {
    		return b.v1 - a.v1;
    	}
    }
}
