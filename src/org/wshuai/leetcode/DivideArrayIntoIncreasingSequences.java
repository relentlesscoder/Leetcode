package org.wshuai.leetcode;

/**
 * Created by Wei on 12/15/2019.
 * #1121 https://leetcode.com/problems/divide-array-into-increasing-sequences/
 */
public class DivideArrayIntoIncreasingSequences {
	public boolean canDivideIntoSubsequences(int[] nums, int K) {
		int prev = 0;
		int maxRepeated = 0;
		int repeat = 0;
		for(int num : nums){
			if(num == prev){
				repeat++;
			}else{
				maxRepeated = Math.max(maxRepeated, repeat);
				repeat = 1;
				prev = num;
			}
		}
		maxRepeated = Math.max(maxRepeated, repeat);
		return maxRepeated * K <= nums.length;
	}
}
