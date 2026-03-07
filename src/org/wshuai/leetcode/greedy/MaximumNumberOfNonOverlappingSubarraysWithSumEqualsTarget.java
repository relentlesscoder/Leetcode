package org.wshuai.leetcode.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/10/2020.
 * #1546 https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/
 */
public class MaximumNumberOfNonOverlappingSubarraysWithSumEqualsTarget {

    // time O(n), space O(n)
    public int maxNonOverlappingDPShort(int[] nums, int target) {
        // 进一步精简代码，因为我们只关心最近的一个合法子数组所以没必要存索引
        // 而是可以直接用哈希表存合法子数组的个数。这样哈希表里的值实际上代表
        // 对应这个前缀和的上一个前缀子数组结尾的索引之前合法子数组的个数。
        int res = 0, n = nums.length;
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            if (dp.containsKey(sum - target)) {
                res = Math.max(res, dp.get(sum - target) + 1);
            }
            dp.put(sum, res);
        }
        return res;
    }

    // time O(n), space O(n)
    public int maxNonOverlappingDP(int[] nums, int target) {
        // 使用前缀和哈希表来优化下面的方案。
        int n = nums.length;
        Map<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, -1);
        int[] dp = new int[n + 1];
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            dp[i + 1] = dp[i];
            if (prefix.containsKey(sum - target)) {
                int idx = prefix.get(sum - target);
                dp[i + 1] = Math.max(dp[i + 1], 1 + dp[idx + 1]);
            }
            prefix.put(sum, i);
        }
        return dp[n];
    }

    // time O(n^2), space O(n)
    public int maxNonOverlappingTLE(int[] nums, int target) {
        // DP：遍历每个数字，如果可以找到一个以当前数字结尾的子数组[j, i]和为
        // 目标值。则可以把以当前索引结束的前缀数组含有的合法子数组的数字更新为
        // 以j结尾的前缀数组含有的合法子数组的数量加一。注意我们无需继续往前查
        // 找因为更长的j有更大机会含有更多合法子数组。
        int n = nums.length;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i];
            // 双重循环会导致超时，可以用前缀和来优化
            for (int sum = 0, j = i; j >= 0; j--) {
                sum += nums[j];
                if (sum == target) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j] + 1);
                    break;
                }
            }
        }
        return dp[n];
    }
}
