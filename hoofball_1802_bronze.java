/*
Bill Zheng
Time: 33 min
USACO Test Cases: **********
1-10 Difficulty: 5
Reflection: This one was a rollercoaster because I actually spent over an hour initially trying to figure out if one method
	worked and the code was really messy and ultimately the algorithm didn't work out in the end I think. So I had to come up
	with a different method after spending so much time on the first solution and I ended up with the code below. It's still
	very messy but I'm just happy that I'm done with this problem. It's very deceiving because it looks easy but isn't.
 */
	
import java.io.*;
import java.util.*;

public class hoofball_1802_bronze {
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner (new File ("hoofball.in"));
//--------------------------------------------------------------------------------------------------------------------------
		int N = scan.nextInt();
		int[] cows = new int[N];
		int[] catches = new int[N]; //array to count how many catches one cow gets
		for (int i = 0; i < N; i++) {
			cows[i] = scan.nextInt();
		}
		catches[1]++; //we won't look at the first and last in the loop so the second and second to last automatically get +1 catches.
		catches[N-2]++;
		Arrays.sort(cows); //sorting cows by distance from barn to get distances easier
//--------------------------------------------------------------------------------------------------------------------------
		int left = 0; int right = 0; //distance between left and right cows
		ArrayList<Integer> pairs = new ArrayList<Integer>(); //need to keep track of cow pairs who pass between each other
		boolean Left = false; boolean Right = true; //checks for cow pairs if the previous throw was to the Right
		
		for (int i = 1; i < N-1; i++) {
			left  = Math.abs(cows[i]-cows[i-1]); //distances between cows on left and right of middle cow
			right = Math.abs(cows[i]-cows[i+1]);
			if (left <= right) {
				catches[i-1]++; //increases cow on left's catches by 1
				if (Right) { //if the previous pass was to the Right and this pass is to the Left, the 2 cows pass between each other
					pairs.add(i-1); //record this pair of cows
				}
				Left = true;
				Right = false;
			} else {
				catches[i+1]++; //increases cow on right's catches by 1
				Right = true;
				Left = false;
			}
		}
		if (Right) { //we can't get to the last cow in the loop so this checks if the last cow is paired with cow N-2
			pairs.add(N-2);
		}
//--------------------------------------------------------------------------------------------------------------------------
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (catches[i] == 0) { //if a cow has 0 catches, they must get a ball to start with
				count++;
			}
		}
		for (int i: pairs) { 
			if (catches[i]==1 && catches[i+1] ==1) { //if both cows in a pair only receive 1 pass each, they are isolated in the game and the farmer need to pass a ball to them initially
				count++;
			}
		}
//--------------------------------------------------------------------------------------------------------------------------
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hoofball.out")));
		out.println(count);

		out.close();
		scan.close();
	}
}
