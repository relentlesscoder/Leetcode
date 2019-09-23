package org.wshuai.leetcode;

/**
 * Created by Wei on 8/20/19.
 * #917 https://leetcode.com/problems/reverse-only-letters/
 */
public class ReverseOnlyLetters {
	public String reverseOnlyLetters(String S) {
		if (S == null || S.length() <= 0) {
			return "";
		}
		char[] arr = S.toCharArray();
		char[] res = new char[arr.length];
		for (int i = 0; i < arr.length; i++) {
			if (Character.isLetter(arr[i])) {
				res[i] = '\\';
			} else {
				res[i] = arr[i];
			}
		}
		int idx = arr.length - 1;
		for (int i = 0; i < arr.length; i++) {
			while (idx > 0 && res[idx] != '\\') {
				idx--;
			}
			if (Character.isLetter(arr[i])) {
				res[idx--] = arr[i];
			} else {
				continue;
			}
		}
		return new String(res);
	}
}
