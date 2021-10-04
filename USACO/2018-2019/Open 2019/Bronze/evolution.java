// Cow Evolution

import java.util.*;
import java.io.*;

public class evolution {
	public static void main(String[] args) throws IOException {
		String file = "evolution";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		ArrayList<String> allChars = new ArrayList<>();
		String[][] pops = new String[N][];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			pops[i] = new String[K];
			
			for(int j = 0; j < K; j++) {
				String characteristic = st.nextToken();
				pops[i][j] = characteristic;
				allChars.add(characteristic);
			}
		}
		br.close();
		
		boolean res = true;
		for(int i = 0; i < allChars.size(); i++) {
			for(int j = i+1; j < allChars.size(); j++) {
				String c1 = allChars.get(i), c2 = allChars.get(j);
				int c1Only = 0, c2Only = 0, both = 0;
				
				for(String[] arr : pops) {
					int c1Count = 0, c2Count = 0;
					for(String characteristic : arr) {
						if(characteristic.equals(c1)) c1Count++;
						if(characteristic.equals(c2)) c2Count++;
					}
					
					if(c1Count > 0 && c2Count > 0) both++;
					else if(c1Count > 0) c1Only++;
					else if(c2Count > 0) c2Only++;
				}
				
				if(c1Only > 0 && c2Only > 0 && both > 0) res = false;
				
			}
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		if(res) out.println("yes");
		else out.println("no");
		out.close();
	}
}
