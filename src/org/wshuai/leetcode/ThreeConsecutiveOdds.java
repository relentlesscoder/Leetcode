package org.wshuai.leetcode;

/**
 * Created by Wei on 08/20/2020.
 * #1550 https://leetcode.com/problems/three-consecutive-odds/
 */
public class ThreeConsecutiveOdds {

	// time O(n)
	public boolean threeConsecutiveOdds(int[] arr) {
		int odd = 0;
		for(int i = 0; i < arr.length; i++){
			odd = arr[i] % 2 == 1 ? odd + 1 : 0;
			if(odd == 3){
				return true;
			}
		}
		return false;
	}
}
