package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 7/28/2017.
 * #645 https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 */
public class ShortestUnsortedContinuousSubarray {
	//Copy array
	public int findUnsortedSubarray(int[] nums) {
		int len = nums.length;
		int[] aux = Arrays.copyOf(nums, len);
		Arrays.sort(aux);
		int left = 0;
		int right = len - 1;
		while (left < len - 1 && aux[left] == nums[left]) {
			left++;
		}
		while (right >= 0 && aux[right] == nums[right]) {
			right--;
		}
		return right > left ? right - left + 1 : 0;
	}
}
