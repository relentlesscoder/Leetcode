package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 03/19/2026.
 * #3825
 * https://leetcode.com/problems/longest-strictly-increasing-subsequence-with-non-zero-bitwise-and/
 */
public class LongestStrictlyIncreasingSubsequenceWithNonZeroBitwiseAnd {

	// time O(m * n * log n), space O(n)
	public int longestSubsequence(int[] nums) {
		// 核心思路：按位拆分问题
		// AND 非零 → 子序列所有元素至少有一个公共的二进制位都为 1
		// 所以枚举每一个 bit 位 i，只考虑第 i 位为 1 的元素，在它们中求最长严格递增子序列（LIS）
		// 所有 bit 位的 LIS 取最大值就是答案
		int res = 0, n = nums.length, max = 0;
		// 找到数组最大值，确定需要枚举多少个 bit 位
		for (int x : nums) {
			max = Math.max(max, x);
		}
		// m = max 的二进制长度，即最高位 1 的位置 + 1
		int m = 32 - Integer.numberOfLeadingZeros(max);
		// 枚举每个 bit 位
		for (int i = 0; i < m; i++) {
			int high = 0;
			// arr 为贪心数组，arr[k] 表示长度为 k+1 的 LIS 的最小可能结尾值
			int[] arr = new int[n];
			for (int j = 0; j < n; j++) {
				// 跳过第 i 位为 0 的元素（它们不可能和其他元素 AND 后在第 i 位为 1）
				if (((1 << i) & nums[j]) == 0) {
					continue;
				}
				// 二分找到 arr 中第一个 >= nums[j] 的位置（lower bound）
				// 用 lower bound 是因为要求严格递增（不允许相等）
				int idx = binarySearch(arr, high, nums[j]);
				if (idx == high) {
					// nums[j] > arr 中所有元素，追加到末尾，延长 LIS
					arr[high++] = nums[j];
				} else {
					// 用 nums[j] 替换 arr[idx]，使该位置结尾值更小，为后续留更多空间
					arr[idx] = nums[j];
				}
			}
			// high 即为第 i 位为 1 的元素中的 LIS 长度，取所有 bit 位的最大值
			res = Math.max(res, high);
		}
		return res;
	}

	private int binarySearch(int[] nums, int high, int target) {
		// lower bound: 找到 nums[0..high) 中第一个 >= target 的位置
		// 用 lower bound 而非 upper bound，因为要求严格递增（相等不行，必须替换）
		int low = 0;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] < target) {
				low = mid + 1; // nums[mid] < target，继续往右找
			} else {
				high = mid; // nums[mid] >= target，收缩右边界
			}
		}
		return low;
	}
}
