package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/09/2015.
 * #0003 https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {

	// time O(n)
	public int lengthOfLongestSubstring(String s) {
		int res = 0;
		int[] index = new int[256];
		Arrays.fill(index, -1);
		for(int i = 0, j = 0; j < s.length(); j++){
			if(index[s.charAt(j)] >= i){
				i = ++index[s.charAt(j)];
			}
			res = Math.max(res, j - i + 1);
			index[s.charAt(j)] = j;
		}
		return res;
	}

	// time O(n)
	public int lengthOfLongestSubstringCount(String s) {
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
