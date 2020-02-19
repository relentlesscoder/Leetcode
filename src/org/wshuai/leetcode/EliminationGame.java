package org.wshuai.leetcode;

/**
 * Created by Wei on 04/03/2017.
 * #0390 https://leetcode.com/problems/elimination-game/
 */
public class EliminationGame {
	// time O(log(n))
	// https://leetcode.com/problems/elimination-game/discuss/87121/O(logN)-solution.-clear-break-down
	public int lastRemaining(int n) {
		return leftToRight(n);
	}

	// eliminate [1...n] first from left to right, then alternate
	private int leftToRight(int n){
		if(n == 1){
			return 1;
		}
		// scan from left to right is simple, the length of array doesn't matter
		// [1, 2, 3, 4] -> 2 * [1, 2]
		// [1, 2, 3, 4, 5] -> 2 * [1, 2]
		return 2 * rightToLeft(n / 2);
	}

	// eliminate [1...n] first from right to left, then alternate
	private int rightToLeft(int n){
		if(n == 1){
			return 1;
		}
		// if the length of array is even, we will get only odd number
		// [1, 2, 3, 4] -> [1, 3] = 2 * [1, 2] - 1
		if(n % 2 == 0){
			return 2 * leftToRight(n / 2) - 1;
			// else if the length of array is odd, we will get only even number
			// [1, 2, 3, 4, 5] -> [2, 4] = 2 * [1, 2]
		}else{
			return 2 * leftToRight(n / 2);
		}
	}
}
