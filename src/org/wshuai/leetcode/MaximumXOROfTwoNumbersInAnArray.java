package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 11/20/2016.
 * #0421 https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */
public class MaximumXOROfTwoNumbersInAnArray {
	// time O(32*n), space O(n)
	// https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91049/Java-O(n)-solution-using-bit-manipulation-and-HashMap
	public int findMaximumXOR(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int mask = 0, max = 0, temp = 0;
		// from MSB to LSB, greedily check if the
		// current bit can be set
		for(int i = 31; i >= 0; i--){
			// use mask the get the left bits
			// e.g. mask 1100, 1101 -> 1100
			mask |= (1 << i);
			// check if 1100 can be the MSB
			// bits of the final result
			temp = max | (1 << i);
			Set<Integer> set = new HashSet<>();
			for(int num : nums){
				set.add(mask & num);
			}
			for(int num : set){
				// if a ^ b = c then a ^ c = b
				// check if there are two numbers
				// can set the candidate MSB above
				int xor = num ^ temp;
				if(set.contains(xor)){
					max = temp;
					break;
				}
			}
		}
		return max;
	}
}
