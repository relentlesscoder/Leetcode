package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2016.
 * #0159 https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {

	// time O(n)
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		int res = 0;
		int[] count = new int[256];
		for(int i = 0, j = 0, n = 0; j < s.length(); j++){
			if(count[s.charAt(j)]++ == 0){
				n++;
			}
			if(n == 3){
				while(--count[s.charAt(i++)] > 0){

				}
				n--;
			}
			res = Math.max(res, j - i + 1);
		}
		return res;
	}
}
