package org.wshuai.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/19/2019.
 * #0967 https://leetcode.com/problems/numbers-with-same-consecutive-differences/
 */
public class NumbersWithSameConsecutiveDifferences {

	// time O(2^n), space O(n)
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        dfs(0, 0, n, k, nums);
        int[] res = new int[nums.size()];
        Arrays.setAll(res, i -> nums.get(i));
        return res;
    }

    private void dfs(int i, int val, int n, int k, List<Integer> nums) {
        if (i == n) {
            nums.add(val);
            return;
        }
        // 只有当前最后一个数字是 k 才可以选 0
        if (val != 0 && Math.abs(val % 10) == k) {
            dfs(i + 1, val * 10, n, k, nums);
        }
        // 对[1, 9]，第一个数字或者与前一个数字绝对差为 k 可以选
        for (int j = 1; j <= 9; j++) {
            if (val == 0 || Math.abs(val % 10 - j) == k) {
                dfs(i + 1, val * 10 + j, n, k, nums);
            }
        }
    }
}
