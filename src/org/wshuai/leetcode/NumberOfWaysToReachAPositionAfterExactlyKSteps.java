package org.wshuai.leetcode;

/**
 * Created by Wei on 01/08/2024.
 * #2400 https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps/
 */
public class NumberOfWaysToReachAPositionAfterExactlyKSteps {

    private static final int MOD = (int)1e9 + 7;

    // time O(n ^ k), space O(n ^ k)
    public int numberOfWaysTopDown(int startPos, int endPos, int k) {
        Integer[][] memo = new Integer[3_000][1_001];
        return dfs(startPos, endPos, k, memo);
    }

    private int dfs(int current, int endPos, int k, Integer[][] memo) {
        if (k < 0 || (k == 0 && current != endPos)) {
            return 0;
        }
        if (k == 0 && current == endPos) {
            return 1;
        }
        if (memo[current + 1_000][k] != null) {
            return memo[current + 1_000][k];
        }
        int res = dfs(current + 1, endPos, k - 1, memo) % MOD;
        res = (res + dfs(current - 1, endPos, k - 1, memo) % MOD) % MOD;
        memo[current + 1_000][k] = res;
        return res;
    }
}
