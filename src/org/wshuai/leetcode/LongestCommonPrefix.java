package org.wshuai.leetcode;

/**
 * Created by Wei on 08/13/2016.
 * #0014 https://leetcode.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {
	// time O(n*m)
	public String longestCommonPrefix(String[] strs) {
		if(strs == null || strs.length == 0){
			return "";
		}
		if(strs.length == 1){
			return strs[0];
		}
		String s = strs[0];
		int n = s.length();
		int i = 0;
		for(; i < n; i++){
			char cur = s.charAt(i);
			for(int j = 1; j < strs.length; j++){
				if(strs[j].length() <= i || strs[j].charAt(i) != cur){
					return s.substring(0, i);
				}
			}
		}
		return s;
	}
}
