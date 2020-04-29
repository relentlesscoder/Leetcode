package org.wshuai.leetcode;

/**
 * Created by Wei on 12/24/2019.
 * #0798 https://leetcode.com/problems/smallest-rotation-with-highest-score/
 */
public class SmallestRotationWithHighestScore {
	// time O(n), space O(n)
	// https://leetcode.com/problems/smallest-rotation-with-highest-score/discuss/118725/Easy-and-Concise-5-lines-Solution-C++JavaPython?page=2
	public int bestRotation(int[] A) {
		int N = A.length;
		int[] change = new int[N];
		/*
		                  2 3 1 4 0
		K = 0,  A = [0,1,2,3,4]     2
		K = 1,  A = [4,0,1,2,3]     3 (+1)
		K = 2,  A = [3,4,0,1,2]     3 (+1 -1)
		K = 3,  A = [2,3,4,0,1]     4 (+1)
		K = 4,  A = [1,2,3,4,0]     3 (+1 -2)
		 */
		for(int i = 0; i < N; i++){
			// index decreases by 1 each time before hits 0
			// so i - A[i] + 1 is the k value that makes A[i]
			// greater than it's index (lose 1 points)
 			change[(i - A[i] + 1 + N) % N] -= 1;
		}
		// set starting points to 0, it's real value does not
		// matter to our problem
		int res = 0;
		for(int i = 1; i < N; i++){
			// change array stores the difference between current
			// and the initial
			change[i] += change[i - 1] + 1;
			res = change[i] > change[res] ? i : res;
		}
		return res;
	}
}
