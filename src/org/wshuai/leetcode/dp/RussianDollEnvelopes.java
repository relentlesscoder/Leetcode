package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 02/20/2017.
 * #0354 https://leetcode.com/problems/russian-doll-envelopes/
 */
public class RussianDollEnvelopes {

	// time O(n log n), space O(n)
	public int maxEnvelopes(int[][] envelopes) {
		// 核心思路：将二维问题降为一维 LIS
		// 排序后只需要对高度求最长严格递增子序列（LIS）
		int n = envelopes.length, high = 0;
		// 排序规则：宽度升序，宽度相同时高度降序
		// 宽度升序：保证遍历时宽度满足递增条件
		// 宽度相同时高度降序：关键！同宽度的信封不能嵌套，高度降序保证同宽度最多只选一个
		// 例如 [3,3] [3,4] 排成 [3,4] [3,3]，对高度求 LIS 时 4→3 不递增，不会同时选中
		Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
		// 排序后问题转化为：对高度数组求 LIS（贪心 + 二分）
		// nums 为贪心数组，nums[k] = 长度为 k+1 的 LIS 的最小可能结尾值
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			// lower bound: 找第一个 >= envelopes[i][1] 的位置（严格递增）
			int idx = binarySearch(nums, high, envelopes[i][1]);
			if (idx == high) {
				// 当前高度 > nums 中所有元素，追加到末尾，延长 LIS
				nums[high++] = envelopes[i][1];
			} else {
				// 用当前高度替换 nums[idx]，使结尾值更小
				nums[idx] = envelopes[i][1];
			}
		}
		// high 即为 LIS 长度 = 最多能嵌套的信封数
		return high;
	}

	private int binarySearch(int[] nums, int high, int target) {
		// lower bound: 找 nums[0..high) 中第一个 >= target 的位置
		int low = 0;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] < target) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}
}
