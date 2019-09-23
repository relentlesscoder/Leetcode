package org.wshuai.leetcode;

/**
 * Created by Wei on 8/8/19.
 * #942 https://leetcode.com/problems/di-string-match/
 */
public class DIStringMatch {
	public int[] diStringMatch(String S) {
		int len = S.length();
		int[] res = new int[len + 1];
		int s = 0;
		int e = len;
		for (int i = 0; i < len; i++) {
			char c = S.charAt(i);
			if (c == 'D') {
				res[i] = e;
				e--;
			} else {
				res[i] = s;
				s++;
			}
		}
		res[len] = s;
		return res;
	}
}
