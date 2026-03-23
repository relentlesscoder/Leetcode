package org.wshuai.leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/10/2019.
 * #1187 https://leetcode.com/problems/make-array-strictly-increasing/
 */
public class MakeArrayStrictlyIncreasing {

	// time O(n * m), space O(n)
	public int makeArrayIncreasingDFSWithMemorization1(int[] arr1, int[] arr2) {
		// 核心思路：换一个角度 —— 不是计算"最少替换次数"，而是计算"最多保留几个 arr1 原值"
		// 答案 = n + 1 - 最多锚点数 (+1 因为 dfs1 返回值包含了虚拟哨兵节点)
		//
		// 把保留原值的位置叫"锚点"，锚点之间的空隙用 arr2 的值填充
		// 例如：arr1 = [1, _, _, 5, _, 8]，锚点是位置 0,3,5 (保留 1,5,8)
		// 位置 1,2 用 arr2 中 (1, 5) 之间的值填充，位置 4 用 arr2 中 (5, 8) 之间的值填充
		//
		// dfs1(i) = 以位置 i 为当前锚点时，位置 0..i 能形成的最多锚点数
		// 从 i = n (虚拟哨兵，值为 MAX_VALUE) 开始搜索
		int n = arr1.length;
		// 去重 + 排序，确保 arr2 严格递增，方便二分和计数
		int[] sorted = Arrays.stream(arr2).distinct().sorted().toArray();
		int[] memo = new int[n + 1];
		int res = dfs1(n, arr1, sorted, memo);
		// res < 0 表示无解；否则 最少替换次数 = 总位置数(n) + 1(哨兵) - 锚点数(res)
		// 因为 n+1 个位置中有 res 个是锚点（保留原值），剩下的都需要替换
		// 但哨兵不是真实位置，所以实际替换次数 = n - (res - 1) = n + 1 - res
		return res < 0 ? -1 : n + 1 - res;
	}

	private int dfs1(int i, int[] arr1, int[] arr2, int[] memo) {
		// dfs1(i) = 以位置 i 为锚点（保留 arr1[i] 或哨兵），位置 0..i 能形成的最多锚点数
		// 返回值已包含位置 i 自身 (通过最后的 ++res)
		if (memo[i] != 0) {
			return memo[i];
		}
		// x = 当前锚点的值 (i=n 时为哨兵 MAX_VALUE，表示右边界无上限)
		int x = i < arr1.length ? arr1[i] : Integer.MAX_VALUE;
		// k = arr2 中严格小于 x 的元素个数（这些元素可以填在位置 i 左边的空隙中）
		int k = binarySearch(arr2, x - 1);
		// res 的初始值对应"位置 i 之前没有任何锚点，0..i-1 全部用 arr2 填充"的情况：
		// k >= i：arr2 中有足够元素填满全部 i 个位置 → res = 0（0 个前置锚点，合法）
		// k < i：arr2 元素不够全填 → res = MIN_VALUE（这个方案不行，但后面找到锚点 j 仍可覆盖）
		int res = k < i ? Integer.MIN_VALUE : 0;
		// 尝试找上一个锚点 j，使得 j..i 之间的空隙能用 arr2 填充
		//
		// 情况一：上一个锚点是位置 i-1 (紧邻，中间无空隙需要填充)
		// 条件：arr1[i-1] < x (锚点值必须严格递增)
		if (i > 0 && arr1[i - 1] < x) {
			res = Math.max(res, dfs1(i - 1, arr1, arr2, memo));
		}
		// 情况二：上一个锚点是位置 j（j < i-1），中间有 i-j-1 个位置需要用 arr2 填充
		// j 从 i-2 往左扫，最远到 i-k-1 (因为最多只有 k 个 arr2 元素可用)
		for (int j = i - 2; j >= i - k - 1 && j >= 0; j--) {
			// 需要 i-j-1 个 arr2 元素填充位置 j+1, j+2, ..., i-1
			// 贪心策略：从 arr2 中选最大的 i-j-1 个（都 < x），按递减顺序分配到 i-1, i-2, ..., j+1
			// arr2 已排序，最大的 i-j-1 个是 arr2[k-1], arr2[k-2], ..., arr2[k-(i-j-1)]
			// 最小的那个 arr2[k-(i-j-1)] 会放在位置 j+1，它必须 > arr1[j] (与上一个锚点严格递增)
			if (arr2[k - (i - j - 1)] > arr1[j]) {
				res = Math.max(res, dfs1(j, arr1, arr2, memo));
			}
		}
		// ++res：加上位置 i 自身这个锚点，返回总锚点数
		// 如果 res 仍为 MIN_VALUE，++res 后仍为负数，表示无解
		return memo[i] = ++res;
	}

	// time O(n * m * log(m)), space O(n * m)
	public int makeArrayIncreasingDFSWithMemorization(int[] arr1, int[] arr2) {
		// 核心思路：记忆化搜索，对每个位置决策"保留原值"还是"替换为 arr2 中的值"
		// 状态：dfs(i, last) = 从位置 i 开始，前一个数为 last 时，使数组严格递增的最少替换次数
		// 贪心选择替换值：替换时选 arr2 中大于 last 的最小值 (给后续留更多空间)
		int n = arr1.length;
		// arr2 排序，方便二分查找大于 last 的最小值
		Arrays.sort(arr2);
		// memo[i] = Map<last, 最少替换次数>，用 Map 因为 last 的值域太大无法用数组
		Map<Integer, Integer>[] memo = new HashMap[n];
		Arrays.setAll(memo, i -> new HashMap<>());
		// 初始 last = -1，表示第一个元素前没有约束
		int res = dfs(0, -1, arr1, arr2, memo);
		// 替换次数超过 2000 说明不可能 (arr1 最长 2000)
		return res > 2000 ? -1 : res;
	}

	private int dfs(int i, int last, int[] arr1, int[] arr2, Map<Integer, Integer>[] memo) {
		// dfs(i, last) = 从位置 i 开始，前一个确定的值为 last，使剩余数组严格递增的最少替换次数
		if (i == arr1.length) {
			return 0; // 所有位置都处理完了，不需要更多替换
		}
		if (memo[i].containsKey(last)) {
			return memo[i].get(last); // 已经计算过，直接返回
		}
		if (arr1[i] > last) {
			// 情况一：arr1[i] > last，当前值本身就满足严格递增
			// 选择 A：不替换，保留 arr1[i]
			int c1 = dfs(i + 1, arr1[i], arr1, arr2, memo);
			// 选择 B：替换为 arr2 中大于 last 的最小值 (可能更优，给后续留更多空间但操作次数 +1)
			int idx = binarySearch(arr2, last);
			if (idx == arr2.length) {
				// arr2 中没有大于 last 的值，只能选择不替换
				memo[i].put(last, c1);
			} else {
				// 两种选择取较小值
				memo[i].put(last, Math.min(c1, 1 + dfs(i + 1, arr2[idx], arr1, arr2, memo)));
			}
		} else {
			// 情况二：arr1[i] <= last，当前值不满足严格递增，必须替换
			// 在 arr2 中找大于 last 的最小值
			int idx = binarySearch(arr2, last);
			if (idx == arr2.length) {
				// arr2 中也没有合适的替换值，无解
				memo[i].put(last, Integer.MAX_VALUE / 2);
			} else {
				// 替换为 arr2[idx]，操作次数 +1
				memo[i].put(last, 1 + dfs(i + 1, arr2[idx], arr1, arr2, memo));
			}
		}
		return memo[i].get(last);
	}

	private int binarySearch(int[] nums, int target) {
		// upper bound: 找 arr2 中第一个严格大于 target 的位置
		// 用于贪心地选择替换值：大于 last 的最小值
		int low = 0, high = nums.length;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] <= target) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}
}
