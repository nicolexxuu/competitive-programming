import java.util.*;
import java.io.*;

public class badmilk {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt(); // people
		int M = in.nextInt(); // types of milk
		int D = in.nextInt(); // drink
		int S = in.nextInt(); // sick
		
		Person []P = new Person[N + 1];
		for(int i = 1; i <= N; i++) {
			P[i] = new Person();
		}
		
		for(int i = 1; i <= D; i++) {
			int p = in.nextInt();
			int m = in.nextInt();
			int t = in.nextInt();
			
			P[p].drinks.add(m);
			P[p].dTimes.add(t);
		}
		int result = 1;
		
		for(int i = 1; i <= S; i++) {
			int p = in.nextInt();
			int t = in.nextInt();
			P[p].tSick = t;
		}
	
		
	int[] counts = new int[D + 1];
	for(int p = 1; p <= N; p++) {
		int t = P[p].tSick;
		int[] freq = new int[D + 1];
		if(t != -1) {
			
			for(int i = 1; i <= D; i++) {
				if(!P[p].drinks.contains(i)) counts[i] = -1;
			}
			for(int i = 0; i < P[p].drinks.size(); i++) {
				freq[P[p].drinks.get(i)]++;
				if(t <= P[p].dTimes.get(i)) counts[P[p].drinks.get(i)] = -1;
				else if (counts[P[p].drinks.get(i)] != -1 && freq[P[p].drinks.get(i)] < 2) {
					counts[P[p].drinks.get(i)]++;
				}
			}
		} else {
			for(int i = 0; i < P[p].drinks.size(); i++) {
				freq[P[p].drinks.get(i)]++;
				if(counts[P[p].drinks.get(i)] != -1 && freq[P[p].drinks.get(i)] < 2)
				counts[P[p].drinks.get(i)]++;
			}
		}
	}
		for(int i: counts) {
			System.out.println(i);
			result = Math.max(i,  result);
		}
		System.out.println(result);
	}
	
	public static class Person{
		ArrayList<Integer> drinks;
		ArrayList<Integer> dTimes;
		int tSick;
		Person(){
			drinks = new ArrayList<Integer>();
			dTimes = new ArrayList<Integer>();
			tSick = -1;
		}
	}
}
