package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2023.
 * #2710 https://leetcode.com/problems/remove-trailing-zeros-from-a-string/
 */
public class RemoveTrailingZerosFromAString {

	// time O(n), space O(n)
	public String removeTrailingZeros(String num) {
		int end = num.length() - 1;
		while (end >= 0 && num.charAt(end) == '0') {
			end--;
		}
		StringBuilder res = new StringBuilder();
		for (int i = 0; i <= end; i++) {
			res.append(num.charAt(i));
		}
		return res.toString();
	}
}
