import java.util.*;
import java.io.*;


public class whereami {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("whereami.in")));
		PrintWriter out = new PrintWriter(new File("whereami.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		String mail = st.nextToken();
		for(int len = 1; len <= N; len++) {
			HashSet<String> seen = new HashSet<>();
			boolean good = true;
			for(int i = 0; i <= N - len; i++) {
				String str = mail.substring(i, i + len);
				//System.out.println(str);
				if(seen.contains(str)) {
					good = false;
					break;
				}
				seen.add(str);
			}
			if(good) {
				//System.out.println(len);
				out.println(len);
				out.close();
				break;
			}
		}
		

	}
}
