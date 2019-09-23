package org.wshuai.leetcode;

/**
 * Created by Wei on 10/2/2016.
 * #76 https://leetcode.com/problems/minimum-window-substring/
 */
public class MinimumWindowSubstring {
	public String minWindow(String s, String t) {
		if (s == null || t == null) {
			return "";
		}

		int sLen = s.length();
		int tLen = t.length();
		if (sLen < tLen) {
			return "";
		}

		int[] expected = new int[256];
		int[] found = new int[256];

		int i = 0;
		while (i < tLen) {
			expected[t.charAt(i)]++;
			i++;
		}

		int minStart = 0;
		int winStart = 0;
		int min = Integer.MAX_VALUE;
		int count = 0;
		for (int winEnd = 0; winEnd < sLen; winEnd++) {
			char curr = s.charAt(winEnd);
			if (expected[curr] > 0) {
				found[curr]++;
				if (expected[curr] >= found[curr]) {
					count++;
				}
			}

			if (count == tLen) {
				char start = s.charAt(winStart);
				while (expected[start] == 0 || found[start] > expected[start]) {
					if (expected[start] > 0) {
						found[start]--;
					}
					winStart++;
					start = s.charAt(winStart);
				}

				int winSize = winEnd - winStart + 1;
				if (winSize < min) {
					min = winSize;
					minStart = winStart;
				}
			}
		}

		if (min == Integer.MAX_VALUE) {
			return "";
		}
		return s.substring(minStart, minStart + min);
	}
}
