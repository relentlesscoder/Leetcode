package org.wshuai.leetcode;

/**
 * Created by Wei on 3/13/17.
 * #541 https://leetcode.com/problems/reverse-string-ii/
 */
public class ReverseStringII {
	public String reverseStr(String s, int k) {
		char[] arr = s.toCharArray();
		int i = 0;
		int len = s.length();
		while (i < len) {
			int j = Math.min(i + k - 1, len - 1);
			swap(i, j, arr);
			i += 2 * k;
		}
		return String.valueOf(arr);
	}

	private void swap(int i, int j, char[] arr) {
		while (i < j) {
			char temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
	}
}
