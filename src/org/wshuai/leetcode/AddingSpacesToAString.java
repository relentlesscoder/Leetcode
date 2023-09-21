package org.wshuai.leetcode;

/**
 * Created by Wei on 09/21/2023.
 * #2109 https://leetcode.com/problems/adding-spaces-to-a-string/
 */
public class AddingSpacesToAString {

	// time O(n + m), space O(n)
	public String addSpaces(String s, int[] spaces) {
		int n = s.length(), m = spaces.length;
		char[] res = new char[n + m];
		for (int i = 0, j = 0, k = 0; i < n; i++) {
			if (j < m && i == spaces[j]) {
				res[k++] = ' ';
				j++;
			}
			res[k++] = s.charAt(i);
		}
		return new String(res);
	}
}
