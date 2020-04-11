package org.wshuai.leetcode;

/**
 * Created by Wei on 07/17/2017.
 * #0557 https://leetcode.com/problems/reverse-words-in-a-string-iii/
 */
public class ReverseWordsInAStringIII {

	// time O(n)
	public String reverseWords(String s) {
		char[] arr = s.toCharArray();
		int i = 0, j = 0;
		for (; j < arr.length; j++) {
			if (arr[j] == ' ') {
				reverse(arr, i, j - 1);
				i = j + 1;
			}
		}
		reverse(arr, i, j - 1);
		return new String(arr);
	}

	private void reverse(char[] arr, int left, int right) {
		while (left < right) {
			char temp = arr[left];
			arr[left++] = arr[right];
			arr[right--] = temp;
		}
	}
}
