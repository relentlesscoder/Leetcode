package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2016.
 * #0374 https://leetcode.com/problems/guess-number-higher-or-lower/
 */
public class GuessNumberHigherOrLower {
	// time O(log(n))
	public int guessNumber(int n) {
		int left = 1, right = n;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(guess(mid) == 1){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}

	// dummy implementation
	private int guess(int num) {
		return 0;
	}
}

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */
