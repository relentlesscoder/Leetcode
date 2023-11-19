package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/11/2023.
 * #2369 https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/
 */
public class CheckIfThereIsAValidPartitionForTheArray {

    // time O(n), space O(n)
    public boolean validPartitionDP(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        for (int i = 2; i <= nums.length; i++) {
            if (dp[i - 2] == 1 && nums[i - 1] == nums[i - 2]) {
                dp[i] = 1;
            } else if (i > 2 && dp[i - 3] == 1
                    && ((nums[i - 1] == nums[i - 2] && nums[i - 2] == nums[i - 3])
                    || (nums[i - 1] == nums[i - 2] + 1 && nums[i - 2] == nums[i - 3] + 1))) {
                dp[i] = 1;
            }
        }
        return dp[n] == 1;
    }

    // time O(2^n), space O(n)
    public boolean validPartitionDFS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return dfs(nums, 0, nums.length, dp);
    }

    private boolean dfs(int[] nums, int curr, int n, int[] dp) {
        if (curr >= n) {
            return curr == n;
        }
        if (dp[curr] != -1) {
            return dp[curr] == 1;
        }
        if (curr + 1 < n && nums[curr] == nums[curr + 1]) {
            if (dfs(nums, curr + 2, n, dp)) {
                dp[curr] = 1;
                return true;
            }
        }
        if ((curr + 2 < n && nums[curr] == nums[curr + 1] && nums[curr + 1] == nums[curr + 2])
                || (curr + 2 < n && nums[curr] + 1 == nums[curr + 1] && nums[curr + 1] + 1 == nums[curr + 2])) {
            if (dfs(nums, curr + 3, n, dp)) {
                dp[curr] = 1;
                return true;
            }
        }
        dp[curr] = 0;
        return false;
    }
}
