package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/07/2024.
 * #1814 https://leetcode.com/problems/count-nice-pairs-in-an-array/
 */
public class CountNicePairsInAnArray {

    // time O(n), space O(n)
    public int countNicePairs(int[] nums) {
        long res = 0, mod = (int)1e9 + 7;
        for (int i = 0; i < nums.length; i++) {
            nums[i] -= reverse(nums[i]);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            res += count;
            map.put(num, count + 1);
        }
        return (int)(res % mod);
    }

    private int reverse(int num) {
        int res = 0;
        while (num > 0) {
            res *= 10;
            res += num % 10;
            num /= 10;
        }
        return res;
    }
}
