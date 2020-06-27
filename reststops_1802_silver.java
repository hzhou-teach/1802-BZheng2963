/*
Bill Zheng
Time: 1 hour 15 min
USACO Test Cases: **********
1-10 Difficulty: 4, really easy solution once you figure out the algorithm
Reflection: This entire problem was pretty easy for silver. Once you figure out the greedy algorithm, the math and implementation/code
	is all really easy. Literally the only problem I had was that I got stuck on 1/10 test cases my first submittion because I didn't
	have longs in my code. All of my variables were integers and so I think it overflowed and didn't work out. I spent like 20 min
	debugging and ended up changing all of my variables to LONG and then instantly got 10/10 test cases.
 */

import java.io.*;
import java.util.*;

class Stops implements Comparable <Stops>{
	public int taste, pos;
	public int compareTo(Stops g) {
		return g.taste - taste; //sorting with the largest taste in front starting at 0 to N.
	}
}

public class reststops_1802_silver {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner (new File ("reststops.in"));
//--------------------------------------------------------------------------------------------------------------------------
		int L = scan.nextInt();
		int N = scan.nextInt();
		int paceJ = scan.nextInt();
		int paceB = scan.nextInt();
		Stops[] stops = new Stops[N];
		
		for (int i = 0; i < N; i++) {
			stops[i] = new Stops();
			stops[i].pos = scan.nextInt();   //assigning the stop's position
			stops[i].taste = scan.nextInt(); //assigning the stop's taste
		}
		Arrays.sort(stops); //sorting the stops so highest taste is first
//--------------------------------------------------------------------------------------------------------------------------
		long tastiness = 0; //ALL VARIABLES ARE LONG
		long location = 0;
		long dist = 0;
		long wait = 0;
		
		for (int i = 0; i < N; i++) {
			if (stops[i].pos < location) { //if the stop is behind where the cow and farmer are, just continue to the next stop
				continue;
			} else {
				dist = stops[i].pos-location; //finds distance from the next stop to where they are
				wait = dist*(paceJ-paceB);    //finds time Bessie waits at the next stop for the farmer
				tastiness += wait*stops[i].taste; //time*taste at stop is added to total tastiness
				location = stops[i].pos; //adjusts new location of Bessie and farmer
			}
		}
//--------------------------------------------------------------------------------------------------------------------------
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));
		out.println(tastiness);

		out.close();
		scan.close();
	}
}
