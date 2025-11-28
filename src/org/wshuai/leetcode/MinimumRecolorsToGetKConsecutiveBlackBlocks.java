package org.wshuai.leetcode;

/**
 * Created by Wei on 01/09/2024.
 * #2379 https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/
 */
public class MinimumRecolorsToGetKConsecutiveBlackBlocks {

    // time O(n), space O(1)
    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length(), res = n, count = 0;
        for (int i = 0; i < n; i++) {
            count += blocks.charAt(i) == 'B' ? 1 : 0;
            if (i - k + 1 < 0) {
                continue;
            }
            res = Math.min(res, k - count);
            count -= blocks.charAt(i - k + 1) == 'B' ? 1 : 0;
        }
        return res;
    }
}
