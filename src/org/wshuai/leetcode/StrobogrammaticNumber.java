package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2016.
 * #0246 https://leetcode.com/problems/strobogrammatic-number/
 */
public class StrobogrammaticNumber {

	// time O(n)
	public boolean isStrobogrammatic(String num) {
		if(num == null || num.length() == 0){
			return true;
		}
		int[] map = new int[]{0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
		for(int i = 0, j = num.length() - 1; i <= j; i++, j--){
			if(map[num.charAt(i) - '0'] != num.charAt(j) - '0'){
				return false;
			}
		}
		return true;
	}
}
