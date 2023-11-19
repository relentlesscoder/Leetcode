package org.wshuai.leetcode;

/**
 * Created by Wei on 02/15/2017.
 * #0485 https://leetcode.com/problems/max-consecutive-ones/
 */
public class MaxConsecutiveOnes {

	// time O(n)
	public int findMaxConsecutiveOnes(int[] nums) {
		int res = 0, count = 0;
		for(int num : nums){
			if(num == 1){
				res = Math.max(res, ++count);
			}else{
				count = 0;
			}
		}
		return res;
	}
}
