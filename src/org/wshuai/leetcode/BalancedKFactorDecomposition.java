package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 02/08/2026.
 * #3669 https://leetcode.com/problems/balanced-k-factor-decomposition/
 */
public class BalancedKFactorDecomposition {

    private int minDiff = Integer.MAX_VALUE;
    private int[] res = new int[0];

    // time O(D^k), space O(k)
    public int[] minDifference(int n, int k) {
        res = new int[k];
        dfs(new ArrayList<>(), 0, Integer.MAX_VALUE, n, k);
        return res;
    }

    private void dfs(List<Integer> nums, int max, int min, int n, int k) {
        if (k == nums.size()) {
            if (n == 1 && max - min < minDiff) {
                minDiff = max - min;
                Arrays.setAll(res, i -> nums.get(i));
            }
            return;
        }
        // 暴力搜索
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                nums.add(i);
                dfs(nums, Math.max(max, i), Math.min(min, i), n / i, k);
                nums.remove(nums.size() - 1);
            }
        }
    }
}
