package org.wshuai.leetcode;

/**
 * Created by Wei on 08/10/2019.
 * #0844 https://leetcode.com/problems/backspace-string-compare/
 */
public class BackspaceStringCompare {

	// time O(n), space O(1)
	public boolean backspaceCompare(String s, String t) {
		int n = s.length(), m = t.length();
		for (int i = n - 1, j = m - 1, cnt1 = 0, cnt2 = 0; i >= 0 || j >= 0;) {
			// cnt1 is the current count of '#', skip the non-backspace characters
			// if cnt1 > 0
			while (i >= 0 && (s.charAt(i) == '#' || cnt1 > 0)) {
				cnt1 += s.charAt(i--) == '#' ? 1 : -1;
			}
			while (j >= 0 && (t.charAt(j) == '#' || cnt2 > 0)) {
				cnt2 += t.charAt(j--) == '#' ? 1 : -1;
			}
			if (i == -1 && j == -1) {
				break;
			}
			if (i == -1 || j == -1 || s.charAt(i--) != t.charAt(j--)) {
				return false;
			}
		}
		return true;
	}
}
