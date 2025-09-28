package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 09/27/2025.
 * #3282 https://leetcode.com/problems/reach-end-of-array-with-max-score/
 */
public class ReachEndOfArrayWithMaxScore {

    // time O(n), space O(1)
    public long findMaximumScore(List<Integer> nums) {
        // https://leetcode.cn/problems/reach-end-of-array-with-max-score/solutions/2908950/yi-tu-miao-dong-tan-xin-pythonjavacgo-by-tfua/
        long res = 0, n = nums.size(), max = 0;
        for (int i = 0; i < n - 1; i++) {
            max = Math.max(max, nums.get(i));
            res += max;
        }
        return res;
    }

    // time O(n^2), space O(n)
    public long findMaximumScoreDP(List<Integer> nums) {
        int n = nums.size();
        int[] dp = new int[n];
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                dp[j] = Math.max(dp[j], dp[i] + nums.get(i) * (j - i));
            }
        }
        return dp[n - 1];
    }
}
