package org.wshuai.leetcode;

/**
 * Created by Wei on 12/30/2025.
 * #3713 https://leetcode.com/problems/longest-balanced-substring-i/
 */
public class LongestBalancedSubstringI {

    // time O(26 * n^2), space O(26)
    public int longestBalanced(String s) {
        // 数组长度不大可以暴力判断每个子数组种不同字符的数量是否一样。
        int res = 0, n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            for (int j = i; j < n; j++) {
                boolean valid = true;
                int curr = ++cnt[arr[j] - 'a'];
                for (int c : cnt) {
                    if (c > 0 && c != curr) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }
}
