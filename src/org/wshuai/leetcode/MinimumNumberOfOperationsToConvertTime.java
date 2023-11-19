package org.wshuai.leetcode;

/**
 * Created by Wei on 11/08/2023.
 * #2224 https://leetcode.com/problems/minimum-number-of-operations-to-convert-time/
 */
public class MinimumNumberOfOperationsToConvertTime {

	// time O(1), space O(1)
	public int convertTime(String current, String correct) {
		int res = 0,
				currentTime = Integer.parseInt(current.substring(0, 2)) * 60 + Integer.parseInt(current.substring(3)),
				correctTime = Integer.parseInt(correct.substring(0, 2)) * 60 + Integer.parseInt(correct.substring(3)),
				diff = correctTime - currentTime;
		res += diff / 60;
		diff %= 60;
		res += diff / 15;
		diff %= 15;
		res += diff / 5;
		diff %= 5;
		res += diff;
		return res;
	}
}
