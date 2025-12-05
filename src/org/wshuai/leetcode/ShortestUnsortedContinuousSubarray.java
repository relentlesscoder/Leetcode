package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 07/28/2017.
 * #0581 https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 */
public class ShortestUnsortedContinuousSubarray {

    // time O(n), space O(1)
    public int findUnsortedSubarray(int[] nums) {
		// 图片 https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/solutions/422614/si-lu-qing-xi-ming-liao-kan-bu-dong-bu-cun-zai-de-/
		int n = nums.length, left = n, right = -1,
				max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		// 从左到右遍历数组并维护当前数字左边的最大值，如果当前数字小于这个最大值说明
		// 当前数字与左边的数字存在逆序所以答案的右边界应该包含当前位置。这个等同于下
		// 面单调栈解法的从右至左的遍历，不同之处在于单调栈解法中栈顶的数字在右。
		for (int i = 0; i < n; i++) {
			max = Math.max(max, nums[i]);
			if (nums[i] < max) {
				right = i;
			}
		}
		// 从右到左遍历数组并维护当前数字左边的最大值，如果当前数字大于这个最小值说明
		// 当前数字与右边的数字存在逆序所以答案的左边界应该包含当前位置。这个等同于下
		// 面单调栈解法的从左至右的遍历。
		for (int i = n - 1; i >= 0; i--) {
			min = Math.min(min, nums[i]);
			if (nums[i] > min) {
				left = i;
			}
		}
		return Math.max(right - left + 1, 0);
    }

	// time O(n), space O(n)
	public int findUnsortedSubarrayMonotonicStack(int[] nums) {
		int n = nums.length, left = n, right = -1;
		Deque<Integer> stack = new ArrayDeque<>();
		// 从左到右遍历数组并维护一个单调递增栈，比较栈顶位置所在的数字与当前数字nums[i]。
		// 如果栈顶更大说明栈顶与当前数字存在逆序所以答案的左边界应该包含栈顶的位置，更新答
		// 案的左端点。
		// 示例：
		//   e.g. [2,6,4,8,1,10,9]
		// 遍历到索引位置2的时候，栈顶索引1所在的数字6大于当前位置的数字4所以更新答案的左端
		// 点为1。
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
				left = Math.min(left, stack.pop());
			}
			stack.push(i);
		}
		stack.clear();
		// 同样的从右到左遍历数组并维护一个单调递减栈，比较栈顶位置所在的数字与当前数字。如果
		// 栈顶更小说明栈顶与当前数字存在逆序所以答案的右边界应该包含栈顶的位置，更新答案的右
		// 端点。
		//   e.g. [2,6,4,8,1,10,9]
		// 遍历到索引位置5的时候，栈顶索引6所在的数字9小于当前位置的数字10所以更新答案的右端
		// 点为6。
		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
				right = Math.max(right, stack.pop());
			}
			stack.push(i);
		}
		return Math.max(right - left + 1, 0);
	}
}
