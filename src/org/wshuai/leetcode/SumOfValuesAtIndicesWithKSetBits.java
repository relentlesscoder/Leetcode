package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 11/16/2023.
 * #2859 https://leetcode.com/problems/sum-of-values-at-indices-with-k-set-bits/
 */
public class SumOfValuesAtIndicesWithKSetBits {

    // time O(n), space O(1)
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.size(); i++) {
            int bits = 0;
            for (int j = 0; j < 10; j++) {
                bits += ((i >> j) & 1);
            }
            if (bits == k) {
                res += nums.get(i);
            }
        }
        return res;
    }
}
