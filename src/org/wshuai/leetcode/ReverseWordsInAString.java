package org.wshuai.leetcode;

/**
 * Created by Wei on 10/25/16.
 * #151 https://leetcode.com/problems/reverse-words-in-a-string/
 */
public class ReverseWordsInAString {
	public String reverseWords(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		char[] chars = s.toCharArray();
		int len = chars.length;
		int end = len - 1;
		int begin = len - 1;
		while (begin >= 0) {
			while (end >= 0 && chars[end] == ' ') {
				end--;
			}
			if (end < 0) {
				break;
			}
			begin = end;
			while (begin >= 0 && chars[begin] != ' ') {
				begin--;
			}
			sb.append(s.substring(begin + 1, end + 1) + " ");
			end = begin;
		}
		return sb.toString().trim();
	}
}
