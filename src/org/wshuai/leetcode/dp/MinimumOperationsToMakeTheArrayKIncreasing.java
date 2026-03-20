package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 03/19/2026.
 * #2111
 * https://leetcode.com/problems/minimum-operations-to-make-the-array-k-increasing/
 */
public class MinimumOperationsToMakeTheArrayKIncreasing {

	// time O(n log n), space O(n/k)
	public int kIncreasing(int[] arr, int k) {
		// 核心思路：k-递增要求 arr[i] <= arr[i+k] <= arr[i+2k] <= ...
		// 即按下标 mod k 分成 k 组，每组内部需要非递减
		// 对每组求最长非递减子序列（LIS 变体），组内长度 - LIS 长度 = 该组需要修改的最少元素数
		int res = 0, n = arr.length;
		// 枚举 k 组，每组的起始下标为 0, 1, ..., k-1
		for (int i = 0; i < k; i++) {
			int cnt = 0, high = 0;
			// nums 复用为贪心数组，记录当前最长非递减子序列的"最优尾部"
			// (n + k - 1) / k 向上取整，是每组的最大元素个数
			int[] nums = new int[(n + k - 1) / k];
			// 遍历第 i 组的所有元素：arr[i], arr[i+k], arr[i+2k], ...
			for (int j = i; j < n; j += k) {
				// 二分找到 nums 中第一个严格大于 arr[j] 的位置（upper bound）
				int idx = binarySearch(nums, high, arr[j]);
				if (idx == high) {
					// arr[j] >= nums 中所有元素，追加到末尾，延长非递减子序列
					nums[high++] = arr[j];
				} else {
					// 用 arr[j] 替换 nums[idx]，使该位置结尾值更小，为后续留更多空间
					nums[idx] = arr[j];
				}
				cnt++; // 该组的元素总数
			}
			// 该组需要修改的元素数 = 组内元素总数 - 最长非递减子序列长度
			res += cnt - high;
		}
		return res;
	}

	private int binarySearch(int[] nums, int high, int target) {
		// 找到 nums[0..high) 中第一个严格大于 target 的位置（upper bound）
		// 用 upper bound 是因为允许相等（非递减），arr[mid] <= target 时继续往右找
		int low = 0;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] <= target) {
				low = mid + 1; // nums[mid] <= target，继续往右找
			} else {
				high = mid; // nums[mid] > target，收缩右边界
			}
		}
		return low;
	}
}
