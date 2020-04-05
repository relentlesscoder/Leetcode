package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 04/05/2020.
 * #1403 https://leetcode.com/problems/minimum-subsequence-in-non-increasing-order/
 */
public class MinimumSubsequenceInNonIncreasingOrder {
	// time O(n*log(n))
	public List<Integer> minSubsequence(int[] nums) {
		int sum = 0, cur = 0, n = nums.length;
		Arrays.sort(nums);
		for(int num : nums){
			sum += num;
		}
		List<Integer> res = new ArrayList<>();
		for(int i = n - 1; i >= 0; i--){
			cur += nums[i];
			res.add(nums[i]);
			if(cur > sum - cur){
				break;
			}
		}
		return res;
	}
}
