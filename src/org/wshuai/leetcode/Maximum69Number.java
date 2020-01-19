package org.wshuai.leetcode;

/**
 * Created by Wei on 01/18/2020.
 * #0000 https://leetcode.com/problems/maximum-69-number/
 */
public class Maximum69Number {
	// time O(n)
	public int maximum69Number (int num) {
		int i = 1, j = 0, n = num;
		while(n > 0){
			if(n % 10 == 6){
				j = i;
			}
			n /= 10;
			i *= 10;
		}
		return j == 0 ? num : num + j * 3;
	}
}
