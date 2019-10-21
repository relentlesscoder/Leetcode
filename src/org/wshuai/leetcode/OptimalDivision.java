package org.wshuai.leetcode;

/**
 * Created by Wei on 10/21/2019.
 * #553 https://leetcode.com/problems/optimal-division/
 */
public class OptimalDivision {

	// math,
	// a2/a3/a4/a5 ... -> a2/(a3*a4*a5 ...)
	// a1/(a2/a3/a4/a5 ...) -> a1/a2 * (a3*a4*a5 ...)
	public String optimalDivision(int[] nums) {
		String res = "" + nums[0];
		if(nums.length == 1){
			return res;
		}
		if(nums.length == 2){
			return res + "/" + nums[1];
		}
		res += "/(" + nums[1];
		for(int i = 2; i < nums.length; i++){
			res += "/" + nums[i];
		}
		res += ")";
		return res;
	}
}
