package org.wshuai.leetcode;

/**
 * Created by Wei on 10/30/2023.
 * #2798 https://leetcode.com/problems/number-of-employees-who-met-the-target/
 */
public class NumberOfEmployeesWhoMetTheTarget {

	// time O(n), space O(1)
	public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
		int res = 0;
		for (int h : hours) {
			res += (h >= target ? 1 : 0);
		}
		return res;
	}
}
