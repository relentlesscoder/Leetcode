package org.wshuai.leetcode;

/**
 * Created by Wei on 08/31/2020.
 * #1558 https://leetcode.com/problems/minimum-numbers-of-function-calls-to-make-target-array/
 */
public class MinimumNumbersOfFunctionCallsToMakeTargetArray {

	// time O(n*log(d))
	public int minOperations(int[] nums) {
		int res = 0, sharedDivide = 0;
		for(int i = 0; i < nums.length; i++){
			int cur = nums[i], curDivide = 0;
			while(cur > 0){
				if(cur % 2 == 0){
					cur /= 2;
					curDivide++;
					// division can be shared
					if(curDivide > sharedDivide){
						sharedDivide = curDivide;
						res++;
					}
				}else{
					// addition cannot be shared
					cur--;
					res++;
				}
			}
		}
		return res;
	}
}
