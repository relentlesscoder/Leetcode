package org.wshuai.leetcode;

/**
 * Created by Wei on 07/29/2017.
 * #0633 https://leetcode.com/problems/sum-of-square-numbers/
 */
public class SumOfSquareNumbers {

    // time O(sqrt(c)), space O(1)
    public boolean judgeSquareSum(int c) {
        long low = 0, high = (long) Math.sqrt(c);
        while (low <= high) {
            long sum = low * low + high * high;
            if (sum == c) {
                return true;
            } else if (sum < c) {
                low++;
            } else {
                high--;
            }
        }
        return false;
    }
}
