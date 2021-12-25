/*
LANG: JAVA
TASK: friday               
*/

import java.util.*;
import java.io.*;

public class friday {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("friday.in"));
		int N = in.nextInt();
		int [] days = new int[8]; // 1 == monday, 7 == sunday
		int month = 1;
		int weekDay = 1;
		int year = 1900;
		int day = 1;
		while(year < 1900 + N) {
			//check today
			if(day == 13) days[weekDay]++;
			//prepare to simulate tomorrow, if past time, break loop
			day++;
			int monthDays = 0;
			if(month == 9 || month == 4 ||month == 6 ||month == 11) {
				monthDays = 30;
			} else {
				if(month == 2 ) {
					if(leapYear(year)) monthDays = 29;
					else monthDays = 28;
				} else {
					monthDays = 31;
				}
			}
			
			if(day > monthDays) {
				day = 1;
				month++;
			}
			
			if(month > 12) {
				month = 1;
				year++;
			}
			
			weekDay++;
			if(weekDay > 7) weekDay = 1;
		}
		PrintWriter out = new PrintWriter(new File("friday.out"));
		out.println(days[6] + " " + days[7] + " " + days[1] + " " + days[2] + " " + days[3] + " " + days[4] + " " + days[5]);
		out.close();
	}
	
	public static boolean leapYear(int year) {
		if(year % 4 == 0) {
			if(year % 100 == 0 && year % 400 != 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
}
