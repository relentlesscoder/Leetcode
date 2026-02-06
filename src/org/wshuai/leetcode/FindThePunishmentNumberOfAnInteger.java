package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2025.
 * #2698 https://leetcode.com/problems/find-the-punishment-number-of-an-integer/
 */
public class FindThePunishmentNumberOfAnInteger {

    private static final int[] PUNISHMENT = new int[1_001];

    // 预处理
    static {
        for (int i = 1; i <= 1_000; i++) {
            PUNISHMENT[i] += PUNISHMENT[i - 1] + (dfs(i, i * i) ? i * i : 0);
        }
    }

    // time O(2^(log_10(n))), space O(log_10(n))
    private static boolean dfs(int target, int num) {
        if (num == 0) {
            return target == 0;
        }
        if (target <= 0) {
            return false;
        }
        // 找下一个分割点
        for (int i = 1, x = 0; num > 0; i *= 10) {
            x += (num % 10) * i; // 计算基于当前分割点的值
            num /= 10;
            if (dfs(target - x, num)) {
                return true;
            }
        }
        return false;
    }

    // time O(1), space O(1)
    public int punishmentNumber(int n) {
        return PUNISHMENT[n];
    }
}
