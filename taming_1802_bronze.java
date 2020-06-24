/*
Bill Zheng
Time: 40 min
USACO Test Cases: **********
1-10 Difficulty: 3, the idea was simple, the implementation is trickier
Reflection: I spent like 30 minutes trying to implement a solution that ultimately didn't work. I think my algorithm once again
	was not functioning correctly so I decided to do a different approach that seemed more messy and time-consuming but it ultimately worked.
	I think trying to take shortcuts and making code quick and simple often misses certain cases because my first quick solution got
	9/10 of the test cases. Once I did a full, thorough solution it worked well.
 */

import java.io.*;
import java.util.*;

public class taming_1802_bronze {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner (new File ("taming.in"));
//--------------------------------------------------------------------------------------------------------------------------
		int N = scan.nextInt();
		int[] log = new int[N];
		for (int i = 0; i < N; i++) {
			log[i] = scan.nextInt();
		}
		log[0] = 0;
//--------------------------------------------------------------------------------------------------------------------------
		int min = 0;
		int max = 0;
		int ret = 0;
		for (int i = 0; i < N; i++) {
			if (log[i] == 0) {
				ret ++;
			}
			
			if (log[i] != 0 && log[i] != -1) {
				while (log[i-1] == -1) {
					log[i-1] = log[i]-1;
					if (log[i-1] == 0) {
						ret ++;
						break;
					} else {
						i--;
					}
				}
				if (log[i-1] != log[i]-1) {
					ret = -1;
					break;
				}
			}
		}
		min = ret;
		max = ret;
		for (int i = 0; i < N; i++) {
			if (log[i] == -1) {
				max ++;
			}
		}
//--------------------------------------------------------------------------------------------------------------------------
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
		if (ret == -1) {
			out.println(-1);
		} else {
			out.println(min + " " + max);
		}
		out.close();
		scan.close();
	}
}
