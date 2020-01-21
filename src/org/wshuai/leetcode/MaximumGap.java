package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 06/07/2017.
 * #0164 https://leetcode.com/problems/maximum-gap/
 */
public class MaximumGap {
	// time O(n), space O(n)
	// bucket sort, see https://leetcode.com/problems/maximum-gap/#/solutions
	public int maximumGap(int[] nums) {
		if(nums == null || nums.length < 2){
			return 0;
		}
		int n = nums.length, max = nums[0], min = nums[0];
		for(int i : nums){
			min = Math.min(min, i);
			max = Math.max(max, i);
		}
		int gap = (int)Math.ceil((double)(max - min)/(n - 1)),
			res = Integer.MIN_VALUE, prev = min;
		int[] bucketsMin = new int[n - 1], bucketsMax = new int[n - 1];
		Arrays.fill(bucketsMin, Integer.MAX_VALUE);
		Arrays.fill(bucketsMax, Integer.MIN_VALUE);
		for(int i : nums){
			if(i == min || i == max){
				continue;
			}
			int idx = (i - min) / gap;
			bucketsMin[idx] = Math.min(i, bucketsMin[idx]);
			bucketsMax[idx] = Math.max(i, bucketsMax[idx]);
		}
		for(int i = 0; i < n - 1; i++){
			if(bucketsMin[i] == Integer.MAX_VALUE ||
				bucketsMax[i] == Integer.MIN_VALUE){
				continue;
			}
			res = Math.max(res, bucketsMin[i] - prev);
			prev = bucketsMax[i];
		}
		res = Math.max(res, max - prev);
		return res;
	}

	// time O(10*n), space O(n + 10)
	// radix sort, https://www.baeldung.com/java-radix-sort
	public int maximumGapRadixSort(int[] nums) {
		if(nums == null || nums.length < 2){
			return 0;
		}
		int res = Integer.MIN_VALUE, n = nums.length, max = nums[0], d = 0, r = 1;
		for(int i : nums){
			max = Math.max(max, i);
		}
		while(max > 0){
			d++;
			max /= 10;
		}
		for(int i = 1; i <= d; i++){
			nums = countingSort(nums, r);
			r *= 10;
		}
		for(int i = 1; i < n; i++){
			res = Math.max(res, nums[i] - nums[i - 1]);
		}
		return res;
	}

	private int[] countingSort(int[] nums, int r){
		int[] freq = new int[10], res = new int[nums.length];
		for(int i : nums){
			freq[(i / r) % 10]++;
		}
		for(int i = 1; i < 10; i++){
			freq[i] += freq[i - 1];
		}
		for(int i = nums.length - 1; i >= 0; i--){
			int idx = (nums[i] / r) % 10;
			res[freq[idx] - 1] = nums[i];
			freq[idx]--;
		}
		return res;
	}
}
