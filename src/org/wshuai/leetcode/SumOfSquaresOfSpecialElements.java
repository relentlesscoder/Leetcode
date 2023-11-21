package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/2023.
 * #2778 https://leetcode.com/problems/sum-of-squares-of-special-elements/
 */
public class SumOfSquaresOfSpecialElements {

    // time O(sqrt(n)), space O(1)
    public int sumOfSquares(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                res += nums[i - 1] * nums[i - 1];
                int div = n / i;
                if (div != i) {
                    res += nums[div - 1] * nums[div - 1];
                }
            }
        }
        return res;
    }
}
