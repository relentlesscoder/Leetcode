package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/09/2015.
 * #0003 https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {
	// time O(n)
	public static int lengthOfLongestSubstring(String s) {
		int res = 0;
		int[] lastIndex = new int[128];
		Arrays.fill(lastIndex, -1);
		int i = 0, j = 0;
		for(; i < s.length(); i++){
			char c = s.charAt(i);
			// the last occurrence needs to be grater than
			// current starting index j
			if(lastIndex[c] >= j){
				res = Math.max(res, i - j);
				j = lastIndex[c] + 1;
			}
			lastIndex[c] = i;
		}
		return Math.max(res, i - j);
	}
}
