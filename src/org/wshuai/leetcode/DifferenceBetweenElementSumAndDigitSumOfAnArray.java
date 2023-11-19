package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2023.
 * #2535 https://leetcode.com/problems/difference-between-element-sum-and-digit-sum-of-an-array/
 */
public class DifferenceBetweenElementSumAndDigitSumOfAnArray {

    // time O(n), space O(1)
    public int differenceOfSum(int[] nums) {
        int sum = 0, digitSum = 0;
        for (int num : nums) {
            sum += num;
            while (num > 0) {
                digitSum += (num % 10);
                num /= 10;
            }
        }
        return Math.abs(sum - digitSum);
    }
}
