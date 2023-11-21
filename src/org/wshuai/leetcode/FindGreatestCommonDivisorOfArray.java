package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/2023.
 * #1979 https://leetcode.com/problems/find-greatest-common-divisor-of-array/
 */
public class FindGreatestCommonDivisorOfArray {

    // time O(n + log(m)), space O(log(m))
    public int findGCD(int[] nums) {
        int max = 0, min = 1_001;
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return gcd(max, min);
    }

    private int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}
