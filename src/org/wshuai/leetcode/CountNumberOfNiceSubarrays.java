package org.wshuai.leetcode;

/**
 * Created by Wei on 11/7/19.
 * #1248 https://leetcode.com/problems/count-number-of-nice-subarrays/
 */
public class CountNumberOfNiceSubarrays {
	public int numberOfSubarrays(int[] nums, int k) {
		int res = 0;
		int i = 0;
		int j = 0;
		int sum = 0;
		while(j < nums.length){
			sum += nums[j] % 2 == 1 ? 1 : 0;
			if(sum == k){
				int l = 1;
				int r = 1;
				while(j < nums.length - 1 && nums[j + 1] % 2 == 0){
					r++;
					j++;
				}
				j++;
				while(nums[i] % 2 == 0){
					l++;
					i++;
				}
				sum--;
				i++;
				res += l * r;
			}else{
				j++;
			}
		}
		return res;
	}
}
