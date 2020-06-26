/*
Bill Zheng
Time: 30 min
USACO Test Cases: 6/12 ******tttttt in 10 min; bad solution was 4/12 ****tttttttt in 20 min; final solution was 7/12 *******ttttt in 30 min
1-10 Difficulty: Can't gage, maybe 8?
Reflection: Since I did the silver version of this problem, getting 7/12 test cases was really easy because it used the same code as before.
	However, I didn't know how to optimize the code to get rid of the remaining 5/12 timeouts. This means that I can't effectively tell
	how hard the problem itself was. What I wrote and came up with was actually really easy, like a 3 because I did it before.
	But since I don't know how to get the FULL solution, the problem is probably a 8 difficulty overall if I had to guess.
 */

import java.io.*;
import java.util.*;

public class snowboots_1802_gold {
	public static void main(String[] args) throws IOException {
		//Scanner scan = new Scanner(new File("D:\\eclipse-workspace\\USACO\\Silver\\testSilver.txt"));
		Scanner scan = new Scanner (new File ("snowboots.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
//--------------------------------------------------------------------------------------------------------------------------
		int N = scan.nextInt();
		int B = scan.nextInt();
		int[] path = new int[N];
		
		for (int i = 0; i < N; i++) {
			path[i] = scan.nextInt();
		}
//--------------------------------------------------------------------------------------------------------------------------
		int depth = 0;
		int steps = 0;
		int loc = 0;
		int farthest = 0;
		int check = 0;
		
		for (int i = 0; i < B; i++) {
			depth = scan.nextInt();
			steps = scan.nextInt();
			loc = 0;
			check = 0;
			
			while (loc < N-1) {
				check = 0;
				farthest = loc + steps; //sees how far the boot can go
				if (farthest > N-1) {
					farthest = N-1; //if the boot goes past the end, just set farthest to the last possible index
				}
				for (int j = farthest; j > loc; j--) {
					if (path[j] <= depth) { //if the boot can reach this index, do it, then repeat the whole process again
						loc = j;
						check = -1;
						break;
					}
				}
				if (check == 0) { //checks to see if the boot COULD NOT move, then it fails, 0
					out.println(0);
					break;
				}
			}
			
			if (loc >= N-1) { //if the boot reaches the end, then it works, 1
				out.println(1);
			}
			
			/*
			// this was the solution I tried afterwards that I THINK is O(B+N) complexity. Correct me if I'm wrong.
			// it is within the other for loop
			int last = 0;
			for (int j = 0; j < N; j++) { //iterate through every snow tile
				
				if (path[j] <= depth) { //if you can move here in 1 step, keep track
					last = j;
				} else { //if you can't move to here in 1 step, that's fine, check if the gap is larger or equal to the boot's step size
					if (j-last >= steps) { //if the gap is larger or equal to boot's step size, it fails, 0
						System.out.println(0);
						out.println(0);
						break;
					}
				}
			}
			if (last == N-1) { //if the boot goes to the very end, it works, 1
				System.out.println(1);
				out.println(1);
			}
			*/
		}
//--------------------------------------------------------------------------------------------------------------------------
		out.close();
		scan.close();
	}
}
