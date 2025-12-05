package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/17/2025.
 * #3480 https://leetcode.com/problems/maximize-subarrays-after-removing-one-conflicting-pair/
 */
public class MaximizeSubarraysAfterRemovingOneConflictingPair {

    // time O(n), space O(n)
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        // https://leetcode.cn/problems/maximize-subarrays-after-removing-one-conflicting-pair/solutions/3603047/mei-ju-zuo-duan-dian-wei-hu-zui-xiao-ci-4nvu6/
        List<Integer>[] groups = new ArrayList[n + 1];
        Arrays.setAll(groups, i -> new ArrayList());
        for (int[] p : conflictingPairs) {
            int a = p[0], b = p[1];
            groups[Math.min(a, b)].add(Math.max(a, b));
        }
        long res = 0, maxExtra = 0, extra = 0;
        int b0 = n + 1, b1 = n + 1;
        for (int i = n; i > 0; i--) {
            int pre = b0;
            for (int b : groups[i]) {
                if (b < b0) {
                    b1 = b0;
                    b0 = b;
                } else if (b < b1) {
                    b1 = b;
                }
            }
            res += b0 - i;
            if (b0 != pre) {
                extra = 0;
            }
            extra += b1 - b0;
            maxExtra = Math.max(maxExtra, extra);
        }
        return res + maxExtra;
    }
}
