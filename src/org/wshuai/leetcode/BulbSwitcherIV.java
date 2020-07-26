package org.wshuai.leetcode;

/**
 * Created by Wei on 07/26/2020.
 * #1529 https://leetcode.com/problems/bulb-switcher-iv/
 */
public class BulbSwitcherIV {

	// time O(n)
	public int minFlips(String target) {
		int res = 0;
		for(int i = 0; i < target.length(); i++){
			if((res % 2 == 0 && target.charAt(i) == '1')
				|| (res % 2 == 1 && target.charAt(i) == '0')){
				res++;
			}
		}
		return res;
	}
}
