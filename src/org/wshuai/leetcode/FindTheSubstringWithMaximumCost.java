package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 01/10/2024.
 * #2606 https://leetcode.com/problems/find-the-substring-with-maximum-cost/
 */
public class FindTheSubstringWithMaximumCost {

    // time O(n + m), space O(1)
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int n = s.length(), m = chars.length();
        // 计算每个字符的开销
        int[] costs = new int[26];
        Arrays.setAll(costs, i -> i + 1);
        for (int i = 0; i < m; i++) {
            costs[chars.charAt(i) - 'a'] = vals[i];
        }
        // 转化为求最大子数组 (#0053)
        int res = 0, maxSum = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            maxSum = Math.max(maxSum + costs[c - 'a'], costs[c - 'a']);
            res = Math.max(res, maxSum);
        }
        return res;
    }
}
