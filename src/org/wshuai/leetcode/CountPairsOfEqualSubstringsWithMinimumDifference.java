package org.wshuai.leetcode;

/**
 * Created by Wei on 11/08/2023.
 * #1794 https://leetcode.com/problems/count-pairs-of-equal-substrings-with-minimum-difference/
 */
public class CountPairsOfEqualSubstringsWithMinimumDifference {

	// time O(n), space O(1)
	public int countQuadruples(String firstString, String secondString) {
		int res = 0, min = Integer.MAX_VALUE, m = firstString.length(), n = secondString.length();
		// If we have a substring "abc" that appears in both strings, then we also have 'a', 'b', 'c' all appear in both strings. If we pick the whole substring "abc",
		// then our score is: index of a + 2 in s1 - index of a in s2, but we can also just pick, say, letter "a", then our score is: index of a in s1 - index of a in s2, which is 2 units smaller than if we pick the whole substring.
		// Basically what this means is that this question is merely asking us to find the matching letters with the farthest distance. It is not a hard problem but more of a trick question.
		int[] count1 = new int[26], count2 = new int[26];
		// we pick the leftmost j to make j - a smaller
		for (int j = m - 1; j >= 0; j--) {
			count1[firstString.charAt(j) - 'a'] = j + 1;
		}
		// we pick the rightmost a to make j - a smaller
		for (int a = 0; a < n; a++) {
			count2[secondString.charAt(a) - 'a'] = a + 1; // +1 to ensure the character exists since 0 is confusing here
		}
		for (int i = 0; i < 26; i++) {
			if (count1[i] == 0 || count2[i] == 0) {
				continue;
			}
			if (count1[i] - count2[i] < min) {
				min = count1[i] - count2[i];
				res = 0;
			}
			res += (min == count1[i] - count2[i] ? 1 : 0);
		}
		return res;
	}
}
