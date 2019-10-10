package org.wshuai.leetcode;

/**
 * Created by Wei on 10/8/19.
 * #696 https://leetcode.com/problems/count-binary-substrings/
 */
public class CountBinarySubstrings {
	public int countBinarySubstrings(String s) {
		int res = 0;
		char prev = '#';
		int p = -1;
		s += "#";
		int last = -1;
		// get the first 1/0 of each 1/0 group
		// difference of two contiguous index identifies
		// the length of a 1/0 group
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) != prev){
				if(p != -1){
					int c = i - p;
					if(last > 0){
						// 11100 contains 2 binary substring
						res += Math.min(c, last);
					}
					last = c;
				}
				prev = s.charAt(i);
				p = i;
			}
		}
		return res;
	}
}
