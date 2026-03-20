package org.wshuai.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 03/18/2026.
 * #2826
 * https://leetcode.com/problems/sorting-three-groups/
 */
public class SortingThreeGroups {

	// time O(n * log(n)), space O(n)
	public int minimumOperationsGreedy(List<Integer> nums) {
		// 核心思路：最少操作次数 = n - 最长非递减子序列长度
		// 因为保留最多的已经有序的元素，剩下的就是需要修改的元素
		// 由于数组元素只有 1, 2, 3，最长非递减子序列等价于最长非递减子序列（LIS 变体）
		// 用贪心 + 二分查找求解，类似 LIS 的 O(n log n) 解法
		int n = nums.size();
		// arr 维护一个有序数组，表示当前找到的最长非递减子序列的"最优尾部"
		// arr[i] 表示长度为 i+1 的非递减子序列的最小可能结尾值
		List<Integer> arr = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			// 二分查找：找到 arr 中第一个严格大于 nums[i] 的位置
			int idx = binarySearch(arr, nums.get(i));
			if (idx == arr.size()) {
				// nums[i] >= arr 中所有元素，可以直接追加，延长非递减子序列
				arr.add(nums.get(i));
			} else {
				// 用 nums[i] 替换 arr[idx]，使得该位置的结尾值更小，为后续元素留更多空间
				arr.set(idx, nums.get(i));
			}
		}
		// 最少操作次数 = 总长度 - 最长非递减子序列长度
		return n - arr.size();
	}

	private int binarySearch(List<Integer> arr, int target) {
		// 找到 arr 中第一个严格大于 target 的位置（upper bound）
		// 之所以用 upper bound 而不是 lower bound，是因为我们允许非递减（相等也可以）
		int low = 0, high = arr.size();
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (arr.get(mid) <= target) {
				low = mid + 1; // arr[mid] <= target，继续往右找
			} else {
				high = mid; // arr[mid] > target，收缩右边界
			}
		}
		return low;
	}
}
