

// http://www.usaco.org/index.php?page=viewproblem2&cpid=206
// "Scrambled Letters"

// This template code suggested by KT BYTE Computer Science Academy
//   for use in reading and writing files for USACO problems.

// https://content.ktbyte.com/problem.java
import java.util.*;
import java.io.*;

public class scramble {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("scramble.in"));
		int N = in.nextInt();
		Word[] list = new Word[N + 1];
		list[0] = new Word("", 0);
		
		for(int i = 1; i <= N; i++) {
			list[i] = new Word(in.next(), i);
		}
		in.close();
		
		for(Word w: list) w.letterSort();
		Arrays.sort(list);
			
		// TODO: sort all strings "early", sort list
	    // TODO: for all strings, find them "late" in that list, store position, ex:
	      for(Word w: list) {
	    	  Word late = new Word(w.w, 0);
	    	  late.reverse();
		      int latePos = Arrays.binarySearch(list, late);
		      // uses built-in compareTo method for Comparable data, finds the position
		      //   if actually present, OR     RV = -IP - 1       if not found
		      //                               IP = -RV - 1      if RV<0 meaning not found

		      if (latePos < 0) latePos = -latePos - 1 - 1;
		      // one of these -1s is for the IP itself; the other is for the adjustment
		      //   due to the duplicate "name" in the list
		      
		      w.latest = latePos;
	      }
	      
	      for(Word w: list) w.reverse();
	      Arrays.sort(list);
	      
	      for(Word w: list) {
	    	  Word early = new Word(w.w, 0);
	    	  early.reverse();
	    	  int earlyPos = Arrays.binarySearch(list,  early);
	    	  if(earlyPos < 0) earlyPos = -earlyPos - 1;
	    	  
	    	  w.earliest = earlyPos;
	      }
		
		Arrays.sort(list, new Comparator<Word>() {
			//anonymous class
			public int compare(Word a, Word b) {
				return a.orig - b.orig;
			}
		});
	      
		
		PrintWriter out = new PrintWriter(new File("scramble.out"));
		for(int i = 1; i <= N; i++) {
			String result = list[i].earliest + " " + list[i].latest;
			System.out.println(result);
			out.println(result);
		}
		
		out.close();
	}
	
	static class Word implements Comparable<Word> {
		String w;
		int orig, earliest, latest;
		
		Word(String w, int orig){
			this.w = w;
			this.orig = orig;
		}
		
		public int compareTo(Word other) {
			return this.w.compareTo(other.w);
		}
		
		void reverse() {
			StringBuilder sb = new StringBuilder();
			for(int i = w.length() - 1; i >= 0; i--) {
				sb.append(w.charAt(i));
			}
			w = sb.toString();
			
		}
		
		void letterSort() {
			char[] letters = w.toCharArray();
			Arrays.sort(letters);
			w = new String(letters);
		}
	}
}


/* ANALYSIS

4
essieb       result: earliest order=beeiss pos = 2           latest pos =
a
xzy
elsie

a                         a
beeiss  <-- pos 2         eeils
sliee                     ssieeb <-- pos 3
zyx                       xyz

bessie sorted early,      bessie sorted late,
others sorted late        others sorted early

unoptimized outline:
for each name: O(n) ~ 50k
   sort other names letters' early, bessie's late - O(n * L log L) ~ 50k * 20 log 20
   sort list of names - O(n log n) ~ 50k * log 50k
   find that name in newly sorted list - O(log n) ~ log 50k
   (do it again in reverse)


a                         a
   beeiss  <-- pos 2      beeiss
sliee                     eeils
ssieeb                        ssieeb <-- pos 3 - 1
zyx                       xyz


a    a (early) found!     a        a (late) found!   both at pos 1
sliee                     beeiss
ssieeb                    eeils
zyx                       xyz
                      

optimized:
pre-sort all names in an early ordering
for each name, search for their late-ordered version in array, store result

pre-sort all names in a late ordering
for each name, search for their early-ordered version, store result

sort all names back to original ordering, print out both results in that order


*/

