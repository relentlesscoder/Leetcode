package org.wshuai.leetcode;

/**
 * Created by Wei on 06/23/2025.
 * #1780 https://leetcode.com/problems/check-if-number-is-a-sum-of-powers-of-three/
 */
public class CheckIfNumberIsASumOfPowersOfThree {

    // time O(log(n)), space O(1)
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }

    // time O(log(n)), space O(log(n))
    public boolean checkPowersOfThreeRecursive(int n) {
        if (n == 1) {
            return true;
        }
        if (n % 3 == 0) {
            return checkPowersOfThree(n /= 3);
        } else if ((n - 1) % 3 == 0) {
            return checkPowersOfThree(n - 1);
        }
        return false;
    }
}
