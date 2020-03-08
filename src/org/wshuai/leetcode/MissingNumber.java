package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2016.
 * #0268 https://leetcode.com/problems/missing-number/
 */
public class MissingNumber {
	// time O(n), space O(1)
	public int missingNumber(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int sum = 0, n = nums.length;
		for(int num : nums){
			sum += num;
		}
		int res = n * (n + 1) / 2;
		res -= sum;
		return res;
	}

	// time O(n), space O(1)
	public int missingNumberBucketSort(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int n = nums.length;
		for(int i = 0; i < n; i++){
			while(nums[i] < n
					&& nums[i] != i
					&& nums[nums[i]] != nums[i]){
				int temp = nums[nums[i]];
				nums[nums[i]] = nums[i];
				nums[i] = temp;
			}
		}
		for(int i = 0; i < n; i++){
			if(nums[i] != i){
				return i;
			}
		}
		return n;
	}
}
