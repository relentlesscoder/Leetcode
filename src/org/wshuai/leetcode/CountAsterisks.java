package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2023.
 * #2315 https://leetcode.com/problems/count-asterisks/
 */
public class CountAsterisks {

	// time O(n), space O(1)
	public int countAsterisks(String s) {
		int res = 0, betweenThePair = 0;
		for (char c : s.toCharArray()) {
			if (c == '*' && betweenThePair == 0) {
				res++;
			} else if (c == '|') {
				betweenThePair = 1 - betweenThePair;
			}
		}
		return res;
	}
}
