/*
Bill Zheng
Time: 6 min
USACO Test Cases: **********
1-10 Difficulty: 1, easiest problem I've ever seen.
Reflection: Nothing was hard, just need to have all 3 cases in the code.
 */

import java.io.*;
import java.util.*;

public class teleport_1802_bronze {
	public static void main(String[] args) throws IOException {
		//Scanner scan = new Scanner(new File("D:\\eclipse-workspace\\USACO\\Silver\\testSilver.txt"));
		Scanner scan = new Scanner (new File ("teleport.in"));
//--------------------------------------------------------------------------------------------------------------------------
		int a = scan.nextInt();
		int b = scan.nextInt();
		int x = scan.nextInt();
		int y = scan.nextInt();
//--------------------------------------------------------------------------------------------------------------------------
		int dist1 = Math.abs(a-b); //no teleporter, only going from start to end
		int dist2 = Math.abs(a-x)+Math.abs(b-y); //go to first  teleport point first
		int dist3 = Math.abs(a-y)+Math.abs(b-x); //go to second teleport point first
//--------------------------------------------------------------------------------------------------------------------------
		int ret = Math.min(dist1, Math.min(dist2, dist3));
//--------------------------------------------------------------------------------------------------------------------------
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));
		System.out.println(ret);
		out.println(ret);

		out.close();
		scan.close();
	}
}
