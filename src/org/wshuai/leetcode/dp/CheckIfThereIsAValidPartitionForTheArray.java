package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 09/11/2023.
 * #2369
 * https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/
 */
public class CheckIfThereIsAValidPartitionForTheArray {

    // time O(n), space O(1)
    public boolean validPartitionDP(int[] nums) {
        // 核心思路：dp[i+1] = nums[0..i] 能否被合法划分
        // 合法子数组只有三种：[a, a]、[a, a, a]、[a, a+1, a+2]
        // 只依赖前 3 个状态，用 3 个变量滚动代替数组
        int n = nums.length;
        // b0, b1, b2 分别对应 dp[i-1], dp[i], dp[i+1]
        // 初始：dp[0] = true (空数组是合法划分)
        boolean b0 = false, b1 = true, b2 = false;
        for (int i = 1; i < n; i++) {
            boolean curr = false;
            // 情况一：nums[i-1], nums[i] 构成 [a, a]，取 2 个元素
            // 前提：dp[i-1] 为 true (前 i-1 个元素能合法划分)
            if (nums[i] == nums[i - 1]) {
                curr = curr || b1; // b1 = dp[i-1]
            }
            // 情况二：nums[i-2], nums[i-1], nums[i] 构成 [a, a, a] 或 [a, a+1, a+2]，取 3 个元素
            // 前提：dp[i-2] 为 true (前 i-2 个元素能合法划分)
            if (i >= 2 && ((nums[i] == nums[i - 1] && nums[i - 1] == nums[i - 2])
                    || (nums[i] == 1 + nums[i - 1] && nums[i - 1] == 1 + nums[i - 2]))) {
                curr = curr || b0; // b0 = dp[i-2]
            }
            // 滚动：b0 ← b1 ← b2 ← curr
            b0 = b1;
            b1 = b2;
            b2 = curr;
        }
        return b2; // dp[n]
    }

    // time O(n), space O(n)
    public boolean validPartitionDPWithArray(int[] nums) {
        // dp[i+1] = true 表示 nums[0..i] 能被合法划分
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // 空数组是合法划分
        for (int i = 1; i < n; i++) {
            // 取最后 2 个元素 [a, a]，检查 dp[i-1]
            if (nums[i] == nums[i - 1]) {
                dp[i + 1] = dp[i + 1] || dp[i - 1];
            }
            // 取最后 3 个元素 [a, a, a] 或 [a, a+1, a+2]，检查 dp[i-2]
            if (i >= 2 && ((nums[i] == nums[i - 1] && nums[i - 1] == nums[i - 2])
                    || (nums[i] == 1 + nums[i - 1] && nums[i - 1] == 1 + nums[i - 2]))) {
                dp[i + 1] = dp[i + 1] || dp[i - 2];
            }
        }
        return dp[n];
    }

    // time O(n), space O(n)
    public boolean validPartitionDFSWithMemorization(int[] nums) {
        // dfs(i) = nums[0..i] 能否被合法划分 (从右往左递归)
        int n = nums.length;
        // 用 Boolean（包装类）区分"未计算"(null) 和"计算结果为 false"
        Boolean[] memo = new Boolean[n];
        return dfs(n - 1, nums, memo);
    }

    private boolean dfs(int i, int[] nums, Boolean[] memo) {
        if (i == -1) {
            return true; // 所有元素都已划分完，合法
        }
        if (i == 0) {
            return false; // 只剩 1 个元素，无法构成任何合法子数组
        }
        if (memo[i] != null) {
            return memo[i];
        }
        boolean res = false;
        // 选最后 2 个元素 nums[i-1], nums[i] 构成 [a, a]，递归检查 nums[0..i-2]
        if (nums[i] == nums[i - 1]) {
            res = res || dfs(i - 2, nums, memo);
        }
        // 选最后 3 个元素构成 [a, a, a] 或 [a, a+1, a+2]，递归检查 nums[0..i-3]
        if (i >= 2 && ((nums[i] == nums[i - 1] && nums[i - 1] == nums[i - 2])
                || (nums[i] == 1 + nums[i - 1] && nums[i - 1] == 1 + nums[i - 2]))) {
            res = res || dfs(i - 3, nums, memo);
        }
        return memo[i] = res;
    }
}
