package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/09/2015.
 * #0003 https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {

	// time O(n)
	public int lengthOfLongestSubstring(String s) {
		int max = 0;
		int[] lastIndex = new int[256];
		Arrays.fill(lastIndex, -1);
		for(int i = 0, j = -1; i < s.length(); i++){
			char c = s.charAt(i);
			j = Math.max(j, lastIndex[c]);
			max = Math.max(max, i - j);
			lastIndex[c] = i;
		}
		return max;
	}

	// time O(n)
	public int lengthOfLongestSubstringSlidingWindow(String s) {
		if(s == null || s.length() == 0){
			return 0;
		}
		int res = 0, n = s.length();
		int[] count = new int[256];
		for(int i = 0, j = 0; i < n; i++){
			count[s.charAt(i)]++;
			while(count[s.charAt(i)] > 1){
				count[s.charAt(j++)]--;
			}
			res = Math.max(res, i - j + 1);
		}
		return res;
	}
}
