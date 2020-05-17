package org.wshuai.leetcode;

/**
 * Created by Wei on 05/17/2020.
 * #1446 https://leetcode.com/problems/consecutive-characters/
 */
public class ConsecutiveCharacters {

	// time O(n)
	public int maxPower(String s) {
		int res = 0, count = 0;
		s += "#";
		char cur = s.charAt(0);
		for(char c : s.toCharArray()){
			if(c == cur){
				count++;
			}else{
				res = Math.max(count, res);
				cur = c;
				count = 1;
			}
		}
		return res;
	}
}
