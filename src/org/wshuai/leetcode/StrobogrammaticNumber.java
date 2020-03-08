package org.wshuai.leetcode;

import java.util.Arrays;

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
		int[] map = new int[10];
		Arrays.fill(map, -1);
		map[0] = 0;
		map[1] = 1;
		map[6] = 9;
		map[8] = 8;
		map[9] = 6;
		for(int i = 0, j = num.length() - 1; i <= j; i++, j--){
			if(map[num.charAt(i) - '0'] != (num.charAt(j) - '0')){
				return false;
			}
		}
		return true;
	}
}
