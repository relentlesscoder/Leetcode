package org.wshuai.leetcode;

/**
 * Created by Wei on 03/09/2020.
 * #1375 https://leetcode.com/problems/bulb-switcher-iii/
 */
public class BulbSwitcherIII {
	// time O(n)
	public int numTimesAllBlue(int[] light) {
		int right = 0, res = 0, n = light.length;
		for(int i = 0; i < n; i++){
			// the rightmost light that is on
			right = Math.max(right, light[i]);
			if(right == i + 1){
				res++;
			}
		}
		return res;
	}
}
