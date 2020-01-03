package org.wshuai.leetcode;

/**
 * Created by Wei on 11/25/19.
 * #1144 https://leetcode.com/problems/decrease-elements-to-make-array-zigzag/
 */
public class DecreaseElementsToMakeArrayZigzag {
	public int movesToMakeZigzag(int[] nums) {
		int odd = 0;
		int even = 0;
		int N = nums.length;
		for(int i = 0; i < N; i++){
			int left = i == 0 ? 1_001 : nums[i - 1];
			int right = i == N - 1 ? 1_001 : nums[i + 1];
			int min = Math.min(left, right);
			if(i % 2 == 0){
				even += nums[i] >= min ? nums[i] - min + 1 : 0;
			}else{
				odd += nums[i] >= min ? nums[i] - min + 1 : 0;
			}
		}
		return Math.min(even, odd);
	}
}
