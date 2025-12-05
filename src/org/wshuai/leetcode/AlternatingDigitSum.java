package org.wshuai.leetcode;

/**
 * Created by Wei on 01/01/2024.
 * #2544 https://leetcode.com/problems/alternating-digit-sum/
 */
public class AlternatingDigitSum {

    // time O(log(n)), space O(log(n))
    public int alternateDigitSum(int n) {
        int res = 0, sign = 1;
        while (n > 0) {
            res += (n % 10) * sign;
            n /= 10;
            sign = -sign;
        }
        return res * -sign;
    }
}
