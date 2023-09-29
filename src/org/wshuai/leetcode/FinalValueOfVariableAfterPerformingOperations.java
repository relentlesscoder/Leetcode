package org.wshuai.leetcode;

/**
 * Created by Wei on 09/29/2023.
 * #2011 https://leetcode.com/problems/final-value-of-variable-after-performing-operations/
 */
public class FinalValueOfVariableAfterPerformingOperations {

	// time O(n), space O(1)
	public int finalValueAfterOperations(String[] operations) {
		int res = 0;
		for (String op : operations) {
			if (op.charAt(0) == '+' || op.charAt(2) == '+') {
				res++;
			} else if (op.charAt(0) == '-' || op.charAt(2) == '-') {
				res--;
			}
		}
		return res;
	}
}
