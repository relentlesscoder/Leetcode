package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2023.
 * #2180 https://leetcode.com/problems/count-integers-with-even-digit-sum/
 */
public class CountIntegersWithEvenDigitSum {

    // time O(1), space O(1)
    public int countEven(int num) {
        // https://leetcode.com/problems/count-integers-with-even-digit-sum/solutions/1784826/java-o-1-solution-only-check-one-number/
        int digitSum = num % 10 + (num / 10) % 10 + (num / 100) % 10 + (num / 1000) % 10;
        return (num - (digitSum & 1)) / 2;
    }
}
