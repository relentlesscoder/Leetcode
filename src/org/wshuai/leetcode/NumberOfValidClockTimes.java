package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2023.
 * #2437 https://leetcode.com/problems/number-of-valid-clock-times/
 */
public class NumberOfValidClockTimes {

	// time O(1), space O(1)
	public int countTime(String time) {
		int res = 1;
		char d1 = time.charAt(0), d2 = time.charAt(1), d3 = time.charAt(3), d4 = time.charAt(4);
		if (d1 == '?' && d2 == '?') {
			res *= 24;
		} else if (d1 == '?') {
			if (d2 >= '0' && d2 <= '3') {
				res *= 3;
			} else {
				res *= 2;
			}
		} else if (d2 == '?') {
			if (d1 == '2') {
				res *= 4;
			} else {
				res *= 10;
			}
		}
		if (d3 == '?' && d4 == '?') {
			res *= 60;
		} else if (d3 == '?') {
			res *= 6;
		} else if (d4 == '?') {
			res *= 10;
		}
		return res;
	}
}
