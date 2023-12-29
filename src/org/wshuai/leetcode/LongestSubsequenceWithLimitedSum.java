package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/29/2023.
 * #2389 https://leetcode.com/problems/longest-subsequence-with-limited-sum/
 */
public class LongestSubsequenceWithLimitedSum {

    // time O(n * log(n) + m * log(n)), space O(1)
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(nums);
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        for (int i = 0; i < m; i++) {
            queries[i] = binarySearch(nums, queries[i]);
        }
        return queries;
    }

    private int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return nums[0] > target ? 0 : low + 1;
    }

    // time O(n * log(n) + m * log(m)), space O(m)
    public int[] answerQueriesSorting(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length, sum = 0;
        Arrays.sort(nums);
        int[][] sorted = new int[m][2];
        for (int i = 0; i < m; i++) {
            sorted[i] = new int[]{queries[i], i};
        }
        Arrays.sort(sorted, (a, b) -> a[0] - b[0]);
        int[] res = new int[m];
        for (int i = 0, j = 0; j < m; j++) {
            while (i < n && sum + nums[i] <= sorted[j][0]) {
                sum += nums[i++];
            }
            res[sorted[j][1]] = i;
        }
        return res;
    }
}
