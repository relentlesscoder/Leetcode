package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 06/20/2020.
 * #1477 https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
 */
public class FindTwoNonOverlappingSubArraysEachWithTargetSum {

    // time O(n), space O(n)
    public int minSumOfLengths(int[] arr, int target) {
        int res = Integer.MAX_VALUE, n = arr.length;
        // 哈希表以前缀和为键，前缀数组的结束索引为值。
        Map<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, -1);
        // 数组dp[i + 1]代表结束索引在i + 1之前子数组和为target的最小长度
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0, sum = 0; i < n; i++) {
            sum += arr[i];
            dp[i + 1] = dp[i]; // 预设为前一个索引的子数组长度最小值
            if (prefix.containsKey(sum - target)) {
                int end = prefix.get(sum - target);
                int l = i - end;
                // 当前缀和索引不为-1并且前缀和为target的子数组最小长度合法 -
                // 和等于target的子数组确定存在
                if (end != -1 && dp[end + 1] != Integer.MAX_VALUE) {
                    // 当前子数组长度 + 当前子数组开始索引之前的最小子数组长度
                    res = Math.min(res, l + dp[end + 1]);
                }
                // 更新当前索引的子数组长度最小值
                dp[i + 1] = Math.min(dp[i + 1], l);
            }
            prefix.put(sum, i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
