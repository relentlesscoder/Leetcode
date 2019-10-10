package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2016.
 * #58 https://leetcode.com/problems/length-of-last-word/
 */
public class LengthOfLastWord {
	public int lengthOfLastWord(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		int r = 0;
		char[] chs = s.toCharArray();
		int len = chs.length;
		int j = len - 1;
		while (j >= 0 && chs[j] == ' ') {
			j--;
		}
		if (j < 0) {
			return 0;
		}
		while (j >= 0 && chs[j] != ' ') {
			r++;
			j--;
		}
		return r;
	}
}
