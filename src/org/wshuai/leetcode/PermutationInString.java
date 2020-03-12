package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2019.
 * #0567 https://leetcode.com/problems/permutation-in-string/
 */
public class PermutationInString {
	// time O(n)
	public boolean checkInclusion(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return false;
		}
		int[] count = new int[26];
		for (char c : s1.toCharArray()) {
			count[c - 'a']++;
		}
		int[] cur = count.clone();
		char[] arr = s2.toCharArray();
		for (int i = 0, j = 0; j < arr.length; j++) {
			char c = arr[j];
			int index = c - 'a';
			// if s1 does not contains current character, then the current substring
			// is invalid and we need restart the search from the next character
			if (count[index] == 0) {
				i = j + 1;
				cur = count.clone();
			} else if (cur[index] > 0) {
				// if current s1 contains current character and all the characters in s1 are
				// visited then we've found a match
				if (--cur[index] == 0) {
					int sum = 0;
					for (int k = 0; k < 26; k++) {
						sum += cur[k];
					}
					if (sum == 0) {
						return true;
					}
				}
			} else {
				// if current s1 contains current character c but we already matched all the c
				// we need to set the head of current search to next character right after the
				// first c from the current head
				while (arr[i] != c) {
					cur[arr[i++] - 'a']++;
				}
				cur[arr[i++] - 'a']++;
				j--;
			}
		}
		return false;
	}
}
