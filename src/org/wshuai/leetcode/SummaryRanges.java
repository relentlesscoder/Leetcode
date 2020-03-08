package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/24/2020.
 * #0228 https://leetcode.com/problems/summary-ranges/
 */
public class SummaryRanges {
	// time O(n)
	public List<String> summaryRanges(int[] nums) {
		List<String> res = new ArrayList<>();
		if(nums == null || nums.length == 0){
			return res;
		}
		int start = nums[0], end = nums[0], n = nums.length;
		for(int i = 1; i < n; i++){
			if(nums[i] == end + 1){
				end = nums[i];
			}else{
				res.add(start == end ? "" + start : start + "->" + end);
				start = end = nums[i];
			}
		}
		res.add(start == end ? "" + start : start + "->" + end);
		return res;
	}
}
