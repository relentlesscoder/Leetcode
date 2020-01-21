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
		if(lower > upper){
			return res;
		}
		int n = nums.length;
		if(n == 0 || (lower > nums[n - 1] || upper < nums[0])){
			res.add(lower == upper ? lower + "" : lower + "->" + upper);
			return res;
		}
		int left = 0, right = n - 1;
		while(left < n && nums[left] < lower){
			left++;
		}
		while(right >= 0 && nums[right] > upper){
			right--;
		}
		if(lower < nums[left]){
			int e = nums[left] - 1;
			res.add(lower == e ? lower + "" : lower + "->" + e);
		}
		for(int i = left; i < right; i++){
			if(nums[i] == nums[i + 1] || nums[i] + 1 == nums[i + 1]){
				continue;
			}
			int s = nums[i] + 1;
			int e = nums[i + 1] - 1;
			res.add(s == e ? s + "" : s + "->" + e);
		}
		if(upper > nums[right]){
			int s = nums[right] + 1;
			res.add(s == upper ? s + "" : s + "->" + upper);
		}
		return res;
	}
}
