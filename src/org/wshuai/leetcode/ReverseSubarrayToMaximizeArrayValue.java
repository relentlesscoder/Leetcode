package org.wshuai.leetcode;

/**
 * Created by Wei on 01/27/2020.
 * #1330 https://leetcode.com/problems/reverse-subarray-to-maximize-array-value/
 */
public class ReverseSubarrayToMaximizeArrayValue {
	// time O(n)
	// https://leetcode.com/problems/reverse-subarray-to-maximize-array-value/discuss/490652/concise-C%2B%2B-O(N)-40ms-beats-100-(-maxmin-minmax-)
	public int maxValueAfterReverse(int[] nums) {
		int sum = 0, minMax = Integer.MAX_VALUE, maxMin = Integer.MIN_VALUE, change = 0, n = nums.length;
		for(int i = 1; i < n; i++){
			int diff = Math.abs(nums[i] - nums[i - 1]);
			sum += diff;
			// see explanation at
			// https://leetcode.com/problems/reverse-subarray-to-maximize-array-value/discuss/489882/O(n)-Solution-with-explanation
			minMax = Math.min(minMax, Math.max(nums[i], nums[i - 1]));
			maxMin = Math.max(maxMin, Math.min(nums[i], nums[i - 1]));
			// reverse the entire left side
			change = Math.max(change, Math.abs(nums[0] - nums[i]) - diff);
			// reverse the entire right side
			change = Math.max(change, Math.abs(nums[n - 1] - nums[i - 1]) - diff);
		}
		return sum + Math.max(change, 2 * (maxMin - minMax));
	}
}
