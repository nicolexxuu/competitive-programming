// Party Invitations

import java.util.*;
import java.io.*;

public class invite {
	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int G = in.nextInt();
		
		ArrayList<HashSet<Integer>> groups = new ArrayList<>();
		boolean[] invited = new boolean[N];
		
		for(int i = 0; i < G; i++) {
			groups.add(new HashSet<Integer>());
			int size = in.nextInt();
			
			for(int j = 0; j < size; j++) {
				int cow = in.nextInt()-1;
				groups.get(i).add(cow);
			}
		}

		int result = 0;
		
		Deque<Integer> toVisit = new ArrayDeque<Integer>();
		toVisit.add(0);
		invited[0] = true;
		
		while(!toVisit.isEmpty()) {
			int curr = toVisit.remove();
			result++;
			
			for(int id = 0; id < G; id++) {
				if(!groups.get(id).contains(curr)) continue;
				HashSet<Integer> group = groups.get(id);
				group.remove(curr);
				if(group.size() == 1) {
					Object[] g = group.toArray();
					int lonely = (Integer)g[0];
					if(!invited[lonely]) {
						toVisit.add(lonely);
						invited[lonely] = true;
					}
				}
			}
		}
		System.out.println(result);
	}

}
