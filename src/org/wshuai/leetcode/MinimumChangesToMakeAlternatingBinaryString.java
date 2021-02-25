package org.wshuai.leetcode;

/**
 * Created by Wei on 02/24/2021.
 * #1758 https://leetcode.com/problems/minimum-changes-to-make-alternating-binary-string/
 */
public class MinimumChangesToMakeAlternatingBinaryString {

	// time O(n)
	public int minOperations(String s) {
		int s1 = 0, s2 = 0, d = 0;
		for(char c : s.toCharArray()){
			if(c - '0' != d){
				s1++;
			}else{
				s2++;
			}
			d = 1 - d;
		}
		return Math.min(s1, s2);
	}
}
