package org.wshuai.leetcode;

/**
 * Created by Wei on 07/26/2020.
 * #1526 https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/
 */
public class MinimumNumberOfIncrementsOnSubarraysToFormATargetArray {

	// time O(n)
	public int minNumberOperations(int[] target) {
		int totalOperation = target[0], operationInCommon = target[0];
		for(int i = 1; i < target.length; i++){
			if(target[i] > operationInCommon){
				totalOperation += target[i] - operationInCommon;
				operationInCommon = target[i];
			}else{
				operationInCommon = target[i];
			}
		}
		return totalOperation;
	}
}
