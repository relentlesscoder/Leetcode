package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/03/2016.
 * #0448 https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 */
public class FindAllNumbersDisappearedInAnArray {
	// time O(n), space O(1)
	// In-place bucket sort
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> res = new ArrayList<>();
		int n = nums.length;
		for(int i = 0; i < n; i++){
			while(nums[i] != i + 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i]){
				int temp = nums[nums[i] - 1];
				nums[nums[i] - 1] = nums[i];
				nums[i] = temp;
			}
		}
		for(int i = 0; i < n; i++){
			if(nums[i] == i + 1){
				continue;
			}
			res.add(i + 1);
		}
		return res;
	}
}
