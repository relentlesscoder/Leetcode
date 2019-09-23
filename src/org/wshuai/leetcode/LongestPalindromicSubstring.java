package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #5 https://leetcode.com/problems/longest-palindromic-substring/
 * https://leetcode.com/articles/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {
	//Manacher's Algorithm
	public String longestPalindrome(String s) {
		if (s == null || s.isEmpty()) {
			throw new IllegalArgumentException("Invalid input");
		}

		StringBuilder sb = new StringBuilder("^");
		int slen = s.length();
		for (int i = 0; i < slen; i++) {
			sb.append("#");
			sb.append(s.charAt(i));
		}
		sb.append("#$");
		String t = sb.toString();

		int sindex = 0;
		int max = -1;

		int center = 0;
		int right = 0;
		int len = t.length();
		int[] arr = new int[len];
		for (int i = 1; i < len - 1; i++) {
			int mirror = 2 * center - i;
			if (right > i) {
				arr[i] = arr[mirror] <= right - i ? arr[mirror] : right - i;
			} else {
				arr[i] = 0;
			}

			while (t.charAt(i + 1 + arr[i]) == t.charAt(i - 1 - arr[i])) {
				arr[i]++;
			}

			if (i + arr[i] > right) {
				center = i;
				right = i + arr[i];
			}

			if (arr[i] > max) {
				max = arr[i];
				sindex = i;
			}
		}

		int index = (sindex - 1 - max) / 2;
		int eindex = index + max;
		if (eindex == slen) {
			return s.substring(index);
		} else {
			return s.substring(index, eindex);
		}
	}
}
