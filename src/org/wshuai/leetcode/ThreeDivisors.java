package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2025.
 * #1952 https://leetcode.com/problems/three-divisors/
 */
public class ThreeDivisors {

    // time O(sqrt(n)), space O(1)
    public boolean isThree(int n) {
        int divisors = 0;
        int i = 1;
        for (; i * i < n; i++) { // check divisors for i * i < n
            if (n % i == 0) { // if 16 % 2 == 0, then both 2 and 8 needs to be counted
                divisors += 2;
            }
        }
        divisors += (i * i == n) ? 1 : 0; // count the last case i * i == n like 4 * 4 = 16
        return divisors == 3;
    }
}
