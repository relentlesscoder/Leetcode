package org.wshuai.leetcode;

/**
 * Created by Wei on 03/13/2017.
 * #0541 https://leetcode.com/problems/reverse-string-ii/
 */
public class ReverseStringII {
	// time O(n/4)
	public String reverseStr(String s, int k) {
		char[] arr = s.toCharArray();
		int n = arr.length, i = 0, d = k << 1;
		while(i < n){
			reverse(arr, i, Math.min(i + k - 1, n - 1));
			i += d;
		}
		return new String(arr);
	}

	private void reverse(char[] arr, int start, int end){
		while(start < end){
			char temp = arr[start];
			arr[start++] = arr[end];
			arr[end--] = temp;
		}
	}
}
