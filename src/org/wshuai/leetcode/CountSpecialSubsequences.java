package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/16/2025.
 * #3404 https://leetcode.com/problems/count-special-subsequences/
 */
public class CountSpecialSubsequences {

    // time O(n^2), space O(n^2)
    public long numberOfSubsequences(int[] nums) {
        // https://leetcode.cn/problems/count-special-subsequences/solutions/3033284/shi-zi-bian-xing-qian-hou-zhui-fen-jie-p-ts6n/
        int n = nums.length;
        long res = 0;
        Map<Double, Integer> map = new HashMap<>();
        // nums[i] is c
        for (int i = 4; i < n; i++) {
            double b = nums[i - 2];
            // nums[j] is a
            for (int j = 0; j < i - 3; j++) {
                map.merge(nums[j] / b, 1, Integer::sum);
            }
            double c = nums[i];
            // nums[j] is d
            for (int j = i + 2; j < n; j++) {
                res += map.getOrDefault(nums[j] / c, 0);
            }
        }
        return res;
    }

    // time O(n^2 * log(MAX)), space O(n^2)
    public long numberOfSubsequencesGCD(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> suf = new HashMap<>();
        for (int i = 4; i <= n - 2; i++) {
            int c = nums[i];
            for (int j = i + 2; j < n; j++) {
                int d = nums[j];
                int g = gcd(c, d);
                suf.merge(((d / g) << 16) | (c / g), 1, Integer::sum);
            }
        }
        long res = 0;
        for (int i = 2; i < n - 4; i++) {
            int b = nums[i];
            for (int j = 0; j < i - 1; j++) {
                int a = nums[j];
                int g = gcd(a, b);
                res += suf.getOrDefault(((a / g) << 16) | (b / g), 0);
            }

            // Remove all {c,d} pairs that could be used by current {a,b} pair since they
            // are invalid for all future {a,b} pairs.
            int c = nums[i + 2];
            for (int j = i + 4; j < n; j++) {
                int d = nums[j];
                int g = gcd(c, d);
                suf.merge(((d / g) << 16) | (c / g), -1, Integer::sum);
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}
