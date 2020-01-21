package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2016.
 * #0159 https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
	// time O(n), space O(1)
	public int lengthOfLongestSubstringTwoDistinct(String s) {
		if(s == null || s.length() == 0){
			return 0;
		}
		char[] arr = s.toCharArray();
		int res = 0, i = 0, j = 0, c1 = -1, c2 = -1, i1 = -1, i2 = -1;
		for(; i < arr.length; i++){
			int cur = (int)arr[i];
			if(c1 != -1 && c2 != -1 && cur != c1 && cur != c2){
				res = Math.max(res, i - j);
				if(i1 < i2){
					j = i1 + 1;
					c1 = cur;
				}else{
					j = i2 + 1;
					c2 = cur;
				}
			}
			if(c1 == -1 || c1 == cur){
				c1 = cur;
				i1 = i;
			}else{
				c2 = cur;
				i2 = i;
			}
		}
		res = Math.max(res, i - j);
		return res;
	}
}
