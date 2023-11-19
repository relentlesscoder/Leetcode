package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2016.
 * #0159 https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {

	// time O(n)
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		if(s == null || s.length() == 0){
			return 0;
		}
		int res = 0, n = s.length();
		int[] count = new int[256];
		for(int i = 0, j = 0, distinct = 0; j < n; j++){
			if(count[s.charAt(j)]++ == 0){
				distinct++;
			}
			if(distinct > 2){
				while(--count[s.charAt(i++)] > 0);
				distinct--;
			}
			res = Math.max(res, j - i + 1);
		}
		return res;
	}
}
