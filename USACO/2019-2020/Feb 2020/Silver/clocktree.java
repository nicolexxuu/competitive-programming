import java.io.*;
import java.util.*;

public class clocktree {
	
	static int N;
	static Room[] rooms;
	static ArrayList<Integer> sortedRooms;
	
	public static void main(String[] args) throws IOException{
		String file = "clocktree";
		BufferedReader br = new BufferedReader(new FileReader(file + ".in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		rooms = new Room[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int time = Integer.parseInt(st.nextToken());
			rooms[i] = new Room(time % 12);
		}
		
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			rooms[a].linkedTo.add(b);
			rooms[b].linkedTo.add(a);
		}
		
		int res = 0;
		sortedRooms = new ArrayList<Integer>();
		
		for(int p = 0; p < N; p++) {
			reset();
			sortedRooms.clear();
			bfs(p);
			
			for(int i = N - 1; i >= 0; i--) {
				Room curr = rooms[sortedRooms.get(i)];
				if(curr.parent != -1) {
					int difference = (12 - curr.tempTime) % 12;
					rooms[curr.parent].tempTime =  (rooms[curr.parent].tempTime + difference) % 12;
				}
			}
			
			if(rooms[p].tempTime == 0 || rooms[p].tempTime == 1) res++;
			
		}
		
		PrintWriter out = new PrintWriter(new File(file + ".out"));
		out.println(res);
		out.close();
	}
	
	public static void reset() {
		for(Room r: rooms) {
			r.visited = false;
			r.tempTime = r.time;
			r.parent = -1;
		}
	}
	
	public static void bfs(int p) {
		rooms[p].visited = true;
		sortedRooms.add(p);
		int x = 0;
		while(x < sortedRooms.size()) {
			int curr = sortedRooms.get(x);
			
			for(int r: rooms[curr].linkedTo) {
				if(!rooms[r].visited) {
					rooms[r].parent = curr;
					rooms[r].visited = true;
					sortedRooms.add(r);
				}
			}
			
			x++;
		}
	}
	
	public static class Room {
		int time;
		int parent;
		boolean visited;
		int tempTime;
		ArrayList<Integer> linkedTo;
		
		Room(int t){
			time = t;
			linkedTo = new ArrayList<Integer>();
			int parent = -1;
			visited = false;
		}
	}
}
