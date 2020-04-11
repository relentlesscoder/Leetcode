package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 07/06/2017.
 * #0561 https://leetcode.com/problems/array-partition-i/
 */
public class ArrayPartitionI {
	// time O(n*log(n))
	public int arrayPairSum(int[] nums) {
		Arrays.sort(nums);
		int res = 0;
		for(int i = 0; i < nums.length; i += 2){
			res += nums[i];
		}
		return res;
	}
}
