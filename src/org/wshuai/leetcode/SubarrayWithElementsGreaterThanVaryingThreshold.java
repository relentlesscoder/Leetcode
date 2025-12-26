package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 11/27/2025.
 * #2334 https://leetcode.com/problems/subarray-with-elements-greater-than-varying-threshold/
 */
public class SubarrayWithElementsGreaterThanVaryingThreshold {

    // time O(n), space O(n)
    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        // 左哨兵
        stack.push(-1);
        // 根据题意，子数组的最小值应满足条件
        //   min > threshold / k, k <= n
        // 遍历数组，对每一个元素找到以它为最小值的最大子数组的长度k。这样可以使得
        // threshold / k的值更小我们有更大的机会找到符合条件的子数组。维护一个单
        // 调栈，对当前元素分别找到左边和右边第一个小于它的元素l和r。如果条件成立
        //   nums[i] > threshold / (r - l - 1)
        // 则 r - l - 1 即为一个合法的答案。
        for (int r = 0; r <= n; r++) {
            int val = r == n ? Integer.MIN_VALUE : nums[r]; // 右哨兵
            // 注意此处条件 >= 不一定每次都会得到最长子数组但是不影响最终结果。
            // 示例1: nums = [1,3,3,3,1], threshold = 6
            // 索引2会被当作以索引1为最小值的右边界计算一次，但因为以索引3为最
            // 小值的情况会在遍历到索引4的时候计算并不影响最终的结果。
            while (stack.size() > 1 && nums[stack.peek()] >= val) {
                // 当前元素为弹出的元素
                int c = stack.pop();
                // 因为单调栈是单调递增的，所以此时栈顶的元素必小于当前元素
                int l = stack.peek();
                // 计算子数组长度
                int k = r - l - 1;
                if (nums[c] > threshold / k) {
                    return r - l - 1;
                }
            }
            stack.push(r);
        }
        return -1;
    }

    // time O(n), space O(n)
    public int validSubarraySizeTwoPass(int[] nums, int threshold) {
        int n = nums.length;
        int[] left = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < n; i++) {
            while (stack.size() > 1 && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            left[i] = stack.peek();
            stack.push(i);
        }
        stack.clear();
        stack.push(n);
        for (int i = n - 1; i >= 0; i--) {
            while (stack.size() > 1 && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            int l = stack.peek() - left[i] - 1;
            if (nums[i] > threshold / l) {
                return l;
            }
            stack.push(i);
        }
        return -1;
    }

    // time O(n * log(n)), space O(n)
    public int validSubarraySizeUnionFind(int[] nums, int threshold) {
        int n = nums.length;
        int[] roots = new int[n + 1], size = new int[n + 1];
        Arrays.setAll(roots, i -> i);
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (a, b) -> nums[b] - nums[a]);
        // 按元素从大到小遍历原数组的索引。每遍历到一个数，把它与之前已经遍历过的数连起来。因为
        // 之前遍历过的数一定大于等于当前元素，所以可以找到以当前元素为最小值的已遍历的元素组成
        // 的子数组的大小。按照解法1的分析，如果满足 min > threshold / k 则k即为答案。
        for (int id : ids) {
            int r = find(id + 1, roots); // 与右边连起来
            roots[id] = r;
            size[r] += size[id] + 1; // 可以把size[r]看做r左边(不含r)已经遍历过了的元素的数量
            if (nums[id] > threshold / size[r]) {
                return size[r];
            }
        }
        return -1;
    }

    private int find(int x, int[] roots) {
        if (x != roots[x]) {
            roots[x] = find(roots[x], roots);
        }
        return roots[x];
    }
}
