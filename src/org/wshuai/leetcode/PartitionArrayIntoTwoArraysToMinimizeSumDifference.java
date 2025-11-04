package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 11/04/2025.
 * #2035 https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/
 */
public class PartitionArrayIntoTwoArraysToMinimizeSumDifference {

    private int res;

    // time O(2^m * log(m)), space O(m^2)
    public int minimumDifference(int[] nums) {
        res = Integer.MAX_VALUE;
        int n = nums.length, m = n / 2;
        List<Integer>[] arr = new ArrayList[m + 1];
        Arrays.setAll(arr, i -> new ArrayList<>());
        // Enumerate the first half [0, n/2] of nums, add element i to the first partition
        // by adding nums[i] and add element i to the second partition by adding -nums[i]
        // Use DFS to find all possible selections for subarray with indexes in [0, n/2]
        dfs1(nums, 0, 0, 0, m, arr);
        for (int i = 0; i <= m; i++) {
            Collections.sort(arr[i]);
        }
        // Enumerate the second half [n/2 + 1, n - 1] of nums, add element i to the first partition
        // by adding -nums[i] and add element i to the second partition by adding nums[i]
        // Use DFS to find all possible selections for subarray with indexes in [n/2 + 1, n - 1]
        dfs2(nums, m, 0, 0, m, n, arr);
        return res;
    }

    private void dfs1(int[] nums, int i, int sum, int count, int m, List<Integer>[] arr) {
        if (i == m) {
            arr[count].add(sum);
            return;
        }
        dfs1(nums, i + 1, sum + nums[i], count + 1, m, arr);
        dfs1(nums, i + 1, sum - nums[i], count, m, arr);
    }

    private void dfs2(int[] nums, int i, int sum, int count, int m, int n, List<Integer>[] arr) {
        // Termination condition
        if (count > m) {
            return;
        }
        if (i == n) {
            // Note that count now means count number of elements were selected into second partition
            // in the second half. arr[count] denotes count number of elements in first half were
            // selected into first partition meaning n/2 - count number of elements in first half were
            // selected into second partition. Combine the two we have: count + n/2 - count = n/2.
            // Then we use binary search to find the closest sum to get the minimum difference.
            List<Integer> list = arr[count];
            // Find the max sum <= target
            int low = 0, high = list.size() - 1;
            while (low < high) {
                int mid = low + (high - low + 1) / 2;
                if (list.get(mid) <= sum) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
            res = Math.min(res, Math.abs(sum - list.get(low)));
            // Also check the min sum > target
            if (low + 1 < list.size()) {
                res = Math.min(res, Math.abs(sum - list.get(low + 1)));
            }
            return;
        }
        dfs2(nums, i + 1, sum + nums[i], count + 1, m, n, arr);
        dfs2(nums, i + 1, sum - nums[i], count, m, n, arr);
    }
}
