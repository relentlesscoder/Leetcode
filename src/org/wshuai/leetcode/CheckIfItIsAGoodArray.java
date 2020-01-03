package org.wshuai.leetcode;

/**
 * Created by Wei on 12/16/2019.
 * #1250 https://leetcode.com/problems/check-if-it-is-a-good-array/
 */
public class CheckIfItIsAGoodArray {
	// shit math question, https://leetcode.com/problems/check-if-it-is-a-good-array/discuss/419324/Bezout's-Identity
	public boolean isGoodArray(int[] nums) {
		if(nums.length == 1){
			return nums[0] == 1;
		}
		int a = nums[0];
		for(int i = 1; i < nums.length; i++){
			a = gcd(a, nums[i]);
			if(a == 1){
				return true;
			}
		}
		return false;
	}

	private int gcd(int x, int y){
		return x == 0 ? y : gcd(y % x, x);
	}
}
