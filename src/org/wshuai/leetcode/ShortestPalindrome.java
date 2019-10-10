package org.wshuai.leetcode;

/**
 * Created by Wei on 6/27/2017.
 * #214 https://leetcode.com/problems/shortest-palindrome/
 */
public class ShortestPalindrome {
	public String shortestPalindrome(String s) {
		if (s == null || s.isEmpty()) {
			return "";
		}
		char[] arr = s.toCharArray();
		int len = s.length();
		int mid = (len - 1) / 2;
		for (int i = mid; i >= 0; i--) {
			//Two cases:
			// 1. i is the central element, i.e. abcbcba
			// 2. i is the left central element, i.e. abccba
			int left = i;
			int right = i;
			while (arr[left] == arr[right]) {
				left--;
				right++;
				if (left < 0) {
					return new StringBuilder(s.substring(right)).reverse().toString() + s;
				}
			}
			left = i - 1;
			right = i;
			while (arr[left] == arr[right]) {
				left--;
				right++;
				if (left < 0) {
					return new StringBuilder(s.substring(right)).reverse().toString() + s;
				}
			}
		}
		return "";
	}
}
