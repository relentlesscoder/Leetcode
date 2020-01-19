package org.wshuai.leetcode;

/**
 * Created by Wei on 01/08/2020.
 * #0041 https://leetcode.com/problems/first-missing-positive/
 */
public class FirstMissingPositive {
	// time O(n)
	public int firstMissingPositive(int[] nums) {
		int n = nums.length;
		for(int i = 0; i < n; i++){
			while(nums[i] > 0
					&& nums[i] <= n
					&& nums[nums[i] - 1] != nums[i]){
				int temp = nums[nums[i] - 1];
				nums[nums[i] - 1] = nums[i];
				nums[i] = temp;
			}
			/* easier to understand version
			while(nums[i] != i + 1){
                if(nums[i] <= 0){
                    break;
                }
                int j = nums[i] - 1;
                if(j >= n || nums[j] == j + 1){
                    break;
                }
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
			 */
		}
		for(int i = 0; i < n; i++){
			if(nums[i] != i + 1){
				return i + 1;
			}
		}
		return n + 1;
	}
}
