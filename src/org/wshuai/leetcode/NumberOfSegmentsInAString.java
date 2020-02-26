package org.wshuai.leetcode;

/**
 * Created by Wei on 12/04/2016.
 * #0434 https://leetcode.com/problems/number-of-segments-in-a-string/
 */
public class NumberOfSegmentsInAString {
	// time O(n)
	public int countSegments(String s) {
		if(s == null || s.isEmpty()){
			return 0;
		}
		int res = 0;
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')){
				res++;
			}
		}
		return res;
	}
}
