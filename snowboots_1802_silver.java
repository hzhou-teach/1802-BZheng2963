/*
Bill Zheng
Time: 60 min
USACO Test Cases: **x**x*x**
1-10 Difficulty: 6-7
Reflection: So my code/solution did not get all of the test cases, however it did get most of them. In competition I would keep this
	and move on to the next problem because it would be good enough to move on if I did well in the next problem. Overall, I thought
	my solution was good enough but it proved to fail for 3/10 test cases. However, the code was easy to write and understand for me
	so it wasn't too hard. I looked at the actual solution and found that it would have been a bit harder to come up with the correct
	solution. Overall, I'd still take my 7/10 and move on.
 */

import java.io.*;
import java.util.*;

class Boots{
	public int depth, steps;
}

public class snowboots_1802_silver {
	public static void main(String[] args) throws IOException {
		//Scanner scan = new Scanner(new File("D:\\eclipse-workspace\\USACO\\Silver\\testSilver.txt"));
		Scanner scan = new Scanner (new File ("snowboots.in"));
//--------------------------------------------------------------------------------------------------------------------------
		int N = scan.nextInt(); int B = scan.nextInt();
		int[] path = new int[N];
		for (int i = 0; i < N; i++) {
			path[i] = scan.nextInt();
		}
		Boots[] bb = new Boots[B];
		for (int i = 0; i < B; i++) {
			bb[i] = new Boots();
			bb[i].depth = scan.nextInt();
			bb[i].steps = scan.nextInt();
		}
		Stack<Boots> boots = new Stack<Boots>();
		// getting boots in a stack
		for (int i = B-1; i >= 0; i--) {
			boots.push(bb[i]);
		}
//--------------------------------------------------------------------------------------------------------------------------
		int discard = 0;
		Boots current = boots.pop(); //sets current boots to the first one in the stack
		int loc = 0;
		int check = 0;
		int farthest = 0;
		
		while (loc < N-1) { //keeps iterating until we reach the end
			check = 0;
			
			farthest = loc + current.steps; //sees how far the boot can go
			if (farthest > N-1) {
				farthest = N-1; //if the boot goes past the end, just set farthest to the last possible index
			}
			for (int i = farthest; i > loc; i--) {
				if (path[i] <= current.depth) { //if the boot can reach this index, do it
					loc = i;
					check = -1;
					break;
				}
			}
			if (check == -1) continue; //if we have succeeded in moving with current boots, repeat, don't bother looking to next boot

			Boots next = boots.peek(); //if current boots failed to move, check next boot
			farthest = loc + next.steps; //new farthest step for the next boots
			if (farthest > N-1) {
				farthest = N-1;
			}
			
			if (next.depth >= path[loc]) {
				for (int i = farthest; i > loc; i--) {
					if (path[i] <= next.depth) {
						current = boots.pop(); //if these boots work, we make them our current boots, discarding the old ones
						discard++;
						loc = i;
						check = -1;
						//System.out.println("Loc 2: " + loc);
						break;
					}
				}
			} 
			if (check == -1) continue; //if the new boots worked, we don't have to keep going, just repeat as they are the new current boots
			
			boots.pop(); //if the current boots AND the next boots both didn't work, get rid of the next boots and repeat the entire loop
			discard++;
		}
//--------------------------------------------------------------------------------------------------------------------------
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
		out.println(discard);

		out.close();
		scan.close();
	}
}
