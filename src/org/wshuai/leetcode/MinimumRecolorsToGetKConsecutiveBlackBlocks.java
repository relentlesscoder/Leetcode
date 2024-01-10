package org.wshuai.leetcode;

/**
 * Created by Wei on 01/09/2024.
 * #2379 https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/
 */
public class MinimumRecolorsToGetKConsecutiveBlackBlocks {

    // time O(n), space O(1)
    public int minimumRecolors(String blocks, int k) {
        int res = blocks.length(), count = 0;
        for (int i = 0, j = 0; i < blocks.length(); i++) {
            count += blocks.charAt(i) == 'B' ? 1 : 0;
            if (i - j + 1 == k) {
                res = Math.min(res, k - count);
                count -= blocks.charAt(j++) == 'B' ? 1 : 0;
            }
        }
        return res;
    }
}
