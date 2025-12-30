package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/02/2023.
 * #2025 https://leetcode.com/problems/maximum-number-of-ways-to-partition-an-array/
 */
public class MaximumNumberOfWaysToPartitionAnArray {

    // time O(n), space O(n)
    public int waysToPartition(int[] nums, int k) {
        int res = 0, n = nums.length;
        long[] pre = new long[n];
        pre[0] = nums[0];
        Map<Long, Integer> right = new HashMap<>();
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
            right.merge(pre[i - 1], 1, Integer::sum); // 注意pivot不能是最后一个数
        }
        long total = pre[n - 1];
        // 计算不改变数组情况下的合法分割方法数
        if (total % 2 == 0) {
            res += right.getOrDefault(total / 2, 0);
        }
        Map<Long, Integer> left = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int delta = k - nums[i]; // 计算把当前元素变成 k 的变化量
            if ((total - delta) % 2 == 0 && (total + delta) % 2 == 0) {
                // 把 nums[i] 变为 k 后, 数组元素和由 sum 变为 sum + delta。分两种情况讨论:
				//   1. i 左边的前缀和不变, 查询是否有前缀和满足 s[j] = (sum + delta) / 2。
				//   2. i 以及其右边的前缀和全部增加了 delta, 需查询是否有前缀和满足
				//      s[j] + delta = (sum + delta) / 2 即 s[j] = (sum - delta) / 2。
				// 统计每个位置上两种情况的总和，答案即为这些和的最大值。
                res = Math.max(res, right.getOrDefault((total - delta) / 2, 0)
                        + left.getOrDefault((total + delta) / 2, 0));
            }
            // 把当前的前缀和从 right 哈希表中移除
            right.merge(pre[i], -1, Integer::sum);
            // 把当前的前缀和加入到 left 哈希表中
            left.merge(pre[i], 1, Integer::sum);
        }
        return res;
    }
}
