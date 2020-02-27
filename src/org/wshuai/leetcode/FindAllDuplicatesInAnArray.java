package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 01/07/2017.
 * #0442 https://leetcode.com/problems/find-all-duplicates-in-an-array/
 */
public class FindAllDuplicatesInAnArray {
	// time O(n), space O(1)
	// in-place bucket sort
	public List<Integer> findDuplicates(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if(nums == null || nums.length == 0){
			return res;
		}
		int n = nums.length;
		for(int i = 0; i < n; i++){
			while(nums[i] != i + 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i]){
				int temp = nums[nums[i] - 1];
				nums[nums[i] - 1] = nums[i];
				nums[i] = temp;
			}
		}
		for(int i = 0; i < n; i++){
			if(nums[i] != i + 1){
				res.add(nums[i]);
			}
		}
		return res;
	}
}
