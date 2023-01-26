import java.util.*;
import java.io.*;

public class boards {
	public static void main(String[] args) throws IOException {
		String file = "boards";
		BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		Board[] boards = new Board[P*2];
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken()),
					x2 = Integer.parseInt(st.nextToken()), y2 =Integer.parseInt(st.nextToken());
			boards[i*2] = new Board(x1, y1, x2, y2, i, true);
			boards[i*2+1] = new Board(x1, y1, x2, y2, i, false);
		}
		int[] save = new int[P];
		br.close();
		
		Arrays.sort(boards);
		TreeMap<Integer, Integer> saved = new TreeMap<>();
		
		for(Board b : boards) {
			if(b.first) {
				save[b.id] = b.y2 - b.y1 + b.x2 - b.x1;
				Integer best = saved.floorKey(b.y1);
				if(best != null) save[b.id] += saved.get(best);
			} else {
				if(saved.floorKey(b.y2) != null && saved.get(saved.floorKey(b.y2)) >= save[b.id])
					continue;
				
				Iterator<Integer> it = saved.subMap(b.y2, Integer.MAX_VALUE).keySet().iterator();
				while (it.hasNext() && save[b.id] > saved.get(it.next()))
					it.remove();
				
				saved.put(b.y2, save[b.id]);
			}
		}
		
		int res = 2*N - saved.get(saved.floorKey(N));
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File(file + ".out"))));
		System.out.println(res);
		out.println(res);
		out.close();
	}
	
	public static class Board implements Comparable<Board>{
		int x1, y1, x2, y2;
		int id;
		boolean first;
		
		Board(int x1, int y1, int x2, int y2, int id, boolean first) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.first = first;
			this.id = id;
		}
		
		public int compareTo(Board o) {
			int ax = first ? x1 : x2, ay = first ? y1 : y2;
			int bx = o.first ? o.x1 : o.x2, by = o.first ? o.y1 : o.y2;

			return (ax == bx) ? ay - by : ax - bx;
		}
	}
}
