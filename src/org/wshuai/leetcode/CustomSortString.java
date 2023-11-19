package org.wshuai.leetcode;

/**
 * Created by Wei on 09/05/2019.
 * #0791 https://leetcode.com/problems/custom-sort-string/
 */
public class CustomSortString {

	// time O(m + n), space O(m + n)
	public String customSortString(String order, String s) {
		StringBuilder res = new StringBuilder();
		int[] count = new int[26];
		for (char c : s.toCharArray()) {
			count[c - 'a']++;
		}
		for (char c : order.toCharArray()) {
			while (count[c - 'a']-- > 0) {
				res.append(c);
			}
		}
		for (int i = 0; i < 26; i++) {
			while (count[i]-- > 0) {
				res.append((char)(i + 'a'));
			}
		}
		return res.toString();
	}
}
