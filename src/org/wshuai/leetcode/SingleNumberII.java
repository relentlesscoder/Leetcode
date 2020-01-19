package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/2016.
 * #0137 https://leetcode.com/problems/single-number-ii/
 */
public class SingleNumberII {
	// time O(n)
	// https://leetcode.com/problems/single-number-ii/discuss/43295/Detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers
	public int singleNumberBitCounter(int[] nums) {
		int x1 = 0, x2 = 0, mask = 0;
		for(int i : nums){
			x2 ^=  x1 & i;
			x1 ^= i;
			mask = ~(x1 & x2);
			x2 &= mask;
			x1 &= mask;
		}
		return (x1 | x2);
	}

	// O(32 * n)
	public int singleNumberBitContribution(int[] nums) {
		int len = nums.length;
		int single = 0;
		for (int i = 0; i < 32; i++) {
			int sum = 0;
			for (int j = 0; j < len; j++) {
				sum += (nums[j] >> i) & 1;
			}
			single |= (sum % 3) << i;
		}
		return single;
	}
}
