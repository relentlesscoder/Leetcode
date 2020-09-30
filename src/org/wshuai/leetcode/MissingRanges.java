package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/26/2016.
 * #0163 https://leetcode.com/problems/missing-ranges/
 */
public class MissingRanges {

	// time O(n)
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> res = new ArrayList<>();
		if(nums == null || nums.length == 0){
			addRange(lower, upper, res);
			return res;
		}
		int n = nums.length;
		addRange(lower, nums[0] - 1, res);
		for(int i = 1; i < n; i++){
			addRange(nums[i - 1] + 1, nums[i] - 1, res);
		}
		addRange(nums[n - 1] + 1, upper, res);
		return res;
	}

	private void addRange(int low, int high, List<String> res){
		if(low > high){
			return;
		}
		if(low == high){
			res.add(low + "");
			return;
		}
		res.add(low + "->" + high);
	}
}
