package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/22/2023.
 * #1898 https://leetcode.com/problems/maximum-number-of-removable-characters/
 */
public class MaximumNumberOfRemovableCharacters {

	// time O(n * log(m)), space O(1)
	public int maximumRemovals(String s, String p, int[] removable) {
		int n = removable.length, m = s.length(), low = 0, high = n; // set high to n since there is a possibility that we need to remove all characters in removable
		int[] map = new int[m];
		Arrays.fill(map, m);
		for (int i = 0; i < n; i++) {
			map[removable[i]] = i; // record index in "removable" for characters in s
		}
		while (low < high) {
			int mid = (low + high + 1) >> 1, i = 0, j = 0;
			while (i < s.length() && j < p.length()) {
				if (map[i] >= mid && s.charAt(i) == p.charAt(j)) {
					j++;
				}
				i++;
			}
			if (j == p.length()) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}
}
