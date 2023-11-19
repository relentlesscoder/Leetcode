package org.wshuai.leetcode;

/**
 * Created by Wei on 09/26/2019.
 * #0670 https://leetcode.com/problems/maximum-swap/
 */
public class MaximumSwap {

	// time O(n), space O(n)
	public int maximumSwap(int num) {
		int[] index = new int[10];
		char[] digits = Integer.toString(num).toCharArray();
		int n = digits.length;
		for (int i = 0; i < n; i++) {
			index[digits[i] - '0'] = i;
		}
		for (int i = 0; i < n; i++) {
			int d = digits[i] - '0';
			for (int j = 9; j > d; j--) {
				if (index[j] > i) {
					digits[index[j]] = digits[i];
					digits[i] = (char) (j + '0');
					return Integer.parseInt(String.valueOf(digits));
				}
			}
		}
		return num;
	}
}
