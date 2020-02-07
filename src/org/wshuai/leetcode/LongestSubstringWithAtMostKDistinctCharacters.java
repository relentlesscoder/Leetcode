package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2016.
 * #0340 https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
	// time O(n)
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		int res = 0, i = 0, j = 0, nums = 0;
		int[] count = new int[256];
		for(; i < s.length(); i++){
			if(count[s.charAt(i)]++ == 0){
				nums++;
			}
			if(nums > k){
				while(--count[s.charAt(j++)] > 0){
				}
				nums--;
			}
			res = Math.max(res, i - j + 1);
		}
		return res;
	}
}
