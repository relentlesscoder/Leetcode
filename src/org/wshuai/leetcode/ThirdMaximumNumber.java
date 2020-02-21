package org.wshuai.leetcode;

/**
 * Created by Wei on 11/02/2016.
 * #0414 https://leetcode.com/problems/third-maximum-number/
 */
public class ThirdMaximumNumber {
	// time O(n)
	public int thirdMax(int[] nums) {
		long[] res = new long[]{Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE};
		for(int num : nums){
			if(num == res[0] || num == res[1] || num == res[2]){
				continue;
			}
			if(num > res[0]){
				res[2] = res[1];
				res[1] = res[0];
				res[0] = num;
			}else if(num > res[1]){
				res[2] = res[1];
				res[1] = num;
			}else if(num > res[2]){
				res[2] = num;
			}
		}
		return (int)(res[2] == Long.MIN_VALUE ? res[0] : res[2]);
	}
}
