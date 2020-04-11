package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2019.
 * #0555 https://leetcode.com/problems/split-concatenated-strings/
 */
public class SplitConcatenatedStrings {
	// time O(2*n^2*l)
	public String splitLoopedString(String[] strs) {
		String res = "";
		int n = strs.length;
		for (int i = 0; i < n; i++) {
			String rev = new StringBuilder(strs[i]).reverse().toString();
			if (strs[i].compareTo(rev) < 0) {
				strs[i] = rev;
			}
		}
		for (int i = 0; i < n; i++) {
			String rev = new StringBuilder(strs[i]).reverse().toString();
			for (String str : new String[]{strs[i], rev}) {
				for (int k = 0; k < str.length(); k++) {
					StringBuilder sb = new StringBuilder();
					sb.append(str.substring(k));
					for (int j = i + 1; j < n; j++) {
						sb.append(strs[j]);
					}
					for (int j = 0; j < i; j++) {
						sb.append(strs[j]);
					}
					sb.append(str.substring(0, k));
					String temp = sb.toString();
					if (res.compareTo(temp) < 0) {
						res = temp;
					}
				}
			}
		}
		return res;
	}
}
