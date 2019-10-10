package org.wshuai.leetcode;

/**
 * Created by Wei on 9/19/2016.
 * #374 https://leetcode.com/problems/guess-number-higher-or-lower/
 */
public class GuessNumberHigherOrLower {
	private int val;

	public GuessNumberHigherOrLower(int val) {
		this.val = val;
	}

	/* The guess API is defined in the parent class GuessGame.
	 @param num, your guess
	 @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
		int guess(int num); */
	public int guessNumber(int n) {
		return guessNumberUtil(1, n);
	}

	public int guessNumberUtil(int min, int max) {
		int mid = min + (max - min) / 2;
		int r = guess(mid);
		if (r == 0) {
			return mid;
		} else if (r == -1) {
			return guessNumberUtil(min, mid - 1);
		} else {
			return guessNumberUtil(mid + 1, max);
		}
	}

	private int guess(int num) {
		if (num == val) {
			return 0;
		} else if (val < num) {
			return -1;
		} else {
			return 1;
		}
	}
}
