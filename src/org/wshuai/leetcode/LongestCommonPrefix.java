package org.wshuai.leetcode;

/**
 * Created by Wei on 8/13/2016.
 * #14 https://leetcode.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {
	public static String longestCommonPrefix(String[] strs) {
		if (strs == null) {
			throw new IllegalArgumentException("Invalid input");
		}
		int len = strs.length;
		if (len == 0) {
			return "";
		}
		int sLen = 100000;
		for (int i = 0; i < len; i++) {
			if (strs[i] == null || strs[i].isEmpty()) {
				sLen = 0;
				break;
			} else {
				int cLen = strs[i].length();
				sLen = cLen < sLen ? cLen : sLen;
			}
		}
		if (sLen == 0) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < sLen; i++) {
				boolean equal = true;
				char x = strs[0].charAt(i);
				for (int j = 0; j < len; j++) {
					if (strs[j].charAt(i) == x) {
						continue;
					} else {
						equal = false;
						break;
					}
				}
				if (equal) {
					sb.append(x);
				} else {
					break;
				}
			}
			return sb.toString();
		}
	}
}
