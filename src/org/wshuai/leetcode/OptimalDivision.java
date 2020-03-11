package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2019.
 * #0553 https://leetcode.com/problems/optimal-division/
 */
public class OptimalDivision {

	// time O(n)
	public String optimalDivision(int[] nums) {
		int n = nums.length;
		if(n == 1){
			return "" + nums[0];
		}
		if(n == 2){
			return nums[0] + "/" + nums[1];
		}
		// a1/(a2/a3/a4/a5 ...) -> (a1/a2) * (a3*a4*a5 ...)
		StringBuilder sb = new StringBuilder();
		sb.append(nums[0] + "/(" + nums[1]);
		for(int i = 2; i < n; i++){
			sb.append("/" + nums[i]);
		}
		sb.append(")");
		return sb.toString();
	}

}
