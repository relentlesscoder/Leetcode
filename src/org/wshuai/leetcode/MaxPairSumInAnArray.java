package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/26/2023.
 * #2815 https://leetcode.com/problems/max-pair-sum-in-an-array/
 */
public class MaxPairSumInAnArray {

    // time O(n * d), space O(d)
    public int maxSum(int[] nums) {
        int res = -1;
        Map<Integer, int[]> map = new HashMap<>();
        for (int num : nums) {
            int maxDigit = -1, val = num;
            while (val > 0) {
                maxDigit = Math.max(maxDigit, val % 10);
                val /= 10;
            }
            map.putIfAbsent(maxDigit, new int[]{-1, -1});
            int[] top = map.get(maxDigit);
            if (num > top[0]) {
                top[1] = top[0];
                top[0] = num;
            } else if (num > top[1]) {
                top[1] = num;
            }
        }
        for (int k : map.keySet()) {
            int[] top = map.get(k);
            if (top[0] != -1 && top[1] != -1) {
                res = Math.max(res, top[0] + top[1]);
            }
        }
        return res;
    }
}
