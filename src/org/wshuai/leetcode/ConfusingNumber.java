package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 8/20/19.
 * #1056 https://leetcode.com/problems/confusing-number/
 */
public class ConfusingNumber {
	public boolean confusingNumber(int N) {
		int[] map = new int[10];
		Arrays.fill(map, -1);
		map[0] = 0;
		map[1] = 1;
		map[6] = 9;
		map[8] = 8;
		map[9] = 6;
		long x = 0;
		long y = N;
		while(y > 0){
			int i = (int)(y % 10);
			if(map[i] == -1){
				return false;
			}
			x = x * 10 + map[i];
			y /= 10;
		}
		return (int)x != N;
	}
}
