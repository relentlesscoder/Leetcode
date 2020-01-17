package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0260 https://leetcode.com/problems/single-number-iii/
 */
public class SingleNumberIII {
	// time O(n), space O(1)
	// https://leetcode.com/problems/single-number-iii/discuss/68900/Accepted-C%2B%2BJava-O(n)-time-O(1)-space-Easy-Solution-with-Detail-Explanations
	public int[] singleNumber(int[] nums) {
		int xor = 0;
		for(int i : nums){
			xor ^= i;
		}
		// nums & it's two's complement will get the only different set bit
		xor &= ~(xor - 1);
		int[] res = new int[]{0, 0};
		for(int i : nums){
			if((xor & i) == 0){
				res[0] ^= i;
			}else{
				res[1] ^= i;
			}
		}
		return res;
	}
}
