package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2023.
 * #2283 https://leetcode.com/problems/check-if-number-has-equal-digit-count-and-digit-value/
 */
public class CheckIfNumberHasEqualDigitCountAndDigitValue {

	// time O(n), space O(1)
	public boolean digitCount(String num) {
		int[] map = new int[10];
		for (int i = 0; i < num.length(); i++) {
			map[num.charAt(i) - '0']++;
		}
		for (int i = 0; i < num.length(); i++) {
			if (map[i] != num.charAt(i) - '0') {
				return false;
			}
		}
		return true;
	}
}
