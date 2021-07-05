// Promotion Counting

import java.util.*;
import java.io.*;

public class promote {
	static int[] BIT;
	static int pid = 1;
	
	public static void main(String[] args) throws IOException {
		String file = "";
		// BufferedReader br = new BufferedReader(new FileReader(new File(file + ".in")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		BIT = new int[N+1];
		
		Node[] nodes = new Node[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[i] = new Node(i);
			nodes[i].prof = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			nodes[Integer.parseInt(st.nextToken()) - 1].children.add(nodes[i]);
		}
		br.close();
		
		preOrder(nodes[0]);
		Arrays.sort(nodes, (a, b) -> b.prof - a.prof);
		
		int[] res = new int[N];
		
		for(Node n: nodes) {
			res[n.id] = find(n.mc) - find(n.pid);
			add(n.pid);
		}
		
		for(int i: res) System.out.println(i);
	}
	
	public static class Node {
		int mc, prof, pid, id;
		ArrayList<Node> children = new ArrayList<>();
		
		Node (int id) {
			this.id = id;
		}
	}
	
	public static void preOrder(Node n) {
		n.pid = pid;
		
		for(Node to: n.children) {
			pid++;
			preOrder(to);
		}
		
		n.mc = pid;
	}
	
	public static int find (int i) {
		int res = 0;
		while(i > 0) {
			res += BIT[i];
			i -= i & -i;
		}
		
		return res;
	}
	
	public static void add (int i) {
		while(i < BIT.length) {
			BIT[i]++;
			i += i & -i;
		}
	}
}
