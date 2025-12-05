package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/07/2024.
 * #1814 https://leetcode.com/problems/count-nice-pairs-in-an-array/
 */
public class CountNicePairsInAnArray {

    private static final int MOD = (int) 1e9 + 7;

    // time O(n), space O(n)
    public int countNicePairs(int[] nums) {
        int n = nums.length;
        long res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums[i], rev = 0;
            while (num > 0) {
                rev *= 10;
                rev += (num % 10);
                num /= 10;
            }
            int val = nums[i] - rev;
            int count = map.getOrDefault(val, 0);
            if (count > 0) {
                res = (res + count) % MOD;
            }
            map.merge(val, 1, Integer::sum);
        }
        return (int) res;
    }
}
