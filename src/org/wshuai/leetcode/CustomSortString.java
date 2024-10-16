package org.wshuai.leetcode;

/**
 * Created by Wei on 09/05/2019.
 * #0791 https://leetcode.com/problems/custom-sort-string/
 */
public class CustomSortString {

	// time O(m + n), space O(m + n)
	public String customSortString(String order, String s) {
		StringBuilder sb = new StringBuilder();
		int[] count = new int[26];
		for (char c : s.toCharArray()) {
			count[c - 'a']++;
		}
		for (char c : order.toCharArray()) {
			while (count[c - 'a']-- > 0) {
				sb.append(c);
			}
		}
		for (int i = 0; i < 26; i++) {
			while (count[i]-- > 0) {
				sb.append((char) ('a' + i));
			}
		}
		return sb.toString();
	}
}
