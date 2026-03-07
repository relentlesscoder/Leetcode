package org.wshuai.leetcode.other;

import java.util.Arrays;

/**
 * Created by Wei on 03/03/2026.
 * #3685 https://leetcode.cn/problems/subsequence-sum-after-capping-elements/
 */
public class SubsequenceSumAfterCappingElements {

    // time O(k * n + n * log(n) + min(n^2, k * log(n))), space O(n + k)
    public boolean[] subsequenceSumAfterCapping(int[] nums, int k) {
        // 将数组排序则随着 k 的增大会数组前面将有更多的数的原来的值被保留下来
        // 示例1:
        //   [2 3 4 4]
        //   [1 1 1 1] x = 1
        //   [2 2 2 2] x = 2
        //   [2 3 3 3] x = 3
        //   [2 3 4 4] x = 4
        Arrays.sort(nums); // O(n * log(n))
        int n = nums.length;
        boolean[] res = new boolean[n];
        boolean[] dp = new boolean[k + 1];
        dp[0] = true;
        int i = 0;
        for (int x = 1; x <= n; x++) {
            // 增量的计算保留下来的值的 0/1 背包，每次只需计算等于 x 的数因为更小的数已经计算过了。
            while (i < n && nums[i] == x) { // O(n * k)
                for (int j = k; j >= nums[i]; j--) {
                    dp[j] = dp[j] || dp[j - nums[i]]; // 选或者不选
                }
                i++;
            }
            // 对于右边被替换成 x 的数，计算需要选多少个 x 。
            for (int j = 0; j <= Math.min(n - i, k / x); j++) {
                if (dp[k - j * x]) {
                    res[x - 1] = true;
                    break;
                }
            }
        }
        return res;
    }
}
