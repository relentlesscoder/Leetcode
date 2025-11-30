package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2019.
 * #1208 https://leetcode.com/problems/get-equal-substrings-within-budget/
 */
public class GetEqualSubstringsWithinBudget {

    // time O(n), space O(1)
    public int equalSubstring(String s, String t, int maxCost) {
        int res = 0, n = s.length();
        for (int i = 0, j = 0, cost = 0; i < n; i++) {
            char a = s.charAt(i), b = t.charAt(i);
            cost += a < b ? b - a : a - b;
            while (cost > maxCost) {
                char c = s.charAt(j), d = t.charAt(j);
                cost -= c < d ? d - c : c - d;
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
