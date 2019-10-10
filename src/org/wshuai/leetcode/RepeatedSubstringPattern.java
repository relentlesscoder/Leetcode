package org.wshuai.leetcode;

/**
 * Created by Wei on 1/3/2017.
 * #459 https://leetcode.com/problems/repeated-substring-pattern/
 */
public class RepeatedSubstringPattern {
	public boolean repeatedSubstringPattern(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		int i = 1;
		int j = 0;
		int len = str.length();
		int[] arr = new int[len + 1];
		while (i < len) {
			if (str.charAt(i) == str.charAt(j)) {
				arr[++i] = ++j;
			} else if (j == 0) {
				i++;
			} else {
				j = arr[j];
			}
		}
		return (arr[len] > 0) && (arr[len] % (len - arr[len]) == 0);
	}
}
