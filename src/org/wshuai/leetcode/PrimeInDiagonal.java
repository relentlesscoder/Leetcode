package org.wshuai.leetcode;

/**
 * Created by Wei on 01/07/2024.
 * #2614 https://leetcode.com/problems/prime-in-diagonal/
 */
public class PrimeInDiagonal {

    // time O(m * sqrt(n)), space O(1)
    public int diagonalPrime(int[][] nums) {
        int res = 0, m = nums.length;
        for (int i = 0; i < m; i++) {
            if (isPrime(nums[i][i])) {
                res = Math.max(res, nums[i][i]);
            }
            if (isPrime(nums[i][m - 1 - i])) {
                res = Math.max(res, nums[i][m - 1 - i]);
            }
        }
        return res;
    }

    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
