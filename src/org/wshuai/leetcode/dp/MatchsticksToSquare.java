package org.wshuai.leetcode.dp;

import java.util.Arrays;

/**
 * Created by Wei on 10/27/2019.
 * #0473 https://leetcode.com/problems/matchsticks-to-square/
 */
public class MatchsticksToSquare {

    // time O(4^n), space O(n)
    public boolean makesquare(int[] matchsticks) {
		// 优化: 反转数组 - 优先使用长的火柴达到目标长度
        Arrays.sort(matchsticks);
        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }
		// 计算中长度
        int sum = 0;
        for (int x : matchsticks) {
            sum += x;
        }
		// 如总长度不能平分为 4 份则不符合要求
        if (sum % 4 != 0) {
            return false;
        }
        sum /= 4;
        return dfs(0, new int[4], sum, matchsticks);
    }

    private boolean dfs(int i, int[] len, int target, int[] nums) {
        if (i == nums.length) {
            return true;
        }
		// 将当前火柴分别加入四个部分
        for (int j = 0; j < 4; j++) {
            len[j] += nums[i];
            if (len[j] <= target && dfs(i + 1, len, target, nums)) {
                return true;
            }
            len[j] -= nums[i];
        }
        return false;
    }
}
