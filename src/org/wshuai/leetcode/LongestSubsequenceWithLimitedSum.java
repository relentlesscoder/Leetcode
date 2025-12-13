package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/29/2023.
 * #2389 https://leetcode.com/problems/longest-subsequence-with-limited-sum/
 */
public class LongestSubsequenceWithLimitedSum {

    // time O((n + m) * log(n)), space O(log(n))
    public int[] answerQueries(int[] nums, int[] queries) {
        // 为了使子序列更长，元素越小越好。对数组排序然后计算前缀和，然后使用
        // 二分搜索找到大于等于queries[i] + 1的索引。这个索引左边就是符合要
        // 求的最长子序列。
        int n = nums.length, m = queries.length;
        int[] res = new int[m];
        Arrays.sort(nums);
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        for (int i = 0; i < m; i++) {
            res[i] = binarySearch(nums, queries[i] + 1);
        }
        return res;
    }

    private int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length;
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
