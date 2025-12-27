package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/27/2025.
 * #LCCI-17.05 https://leetcode.cn/problems/find-longest-subarray-lcci/
 */
public class FindLongestSubarrayLCCI {

    // time O(n), space O(n)
    public String[] findLongestSubarray(String[] array) {
        // #0525相似题
        int len = 0, idx = -1, n = array.length;
        int[] count = new int[2 * n + 1];
        Arrays.fill(count, n);
        count[n] = -1;
        for (int i = 0, diff = 0; i < n; i++) {
            diff += Character.isDigit(array[i].charAt(0)) ? 1 : -1;
            if (count[diff + n] != n && i - count[diff + n] > len) {
                len = i - count[diff + n];
                idx = count[diff + n] + 1;
            } else if (count[diff + n] == n) {
                count[diff + n] = i;
            }
        }
        if (idx == -1) {
            return new String[0];
        }
        String[] res = new String[len];
        for (int i = 0; i < len; i++) {
            res[i] = array[i + idx];
        }
        return res;
    }
}
