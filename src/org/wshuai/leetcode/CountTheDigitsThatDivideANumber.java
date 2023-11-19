package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2023.
 * #2520 https://leetcode.com/problems/count-the-digits-that-divide-a-number/
 */
public class CountTheDigitsThatDivideANumber {

    // time O(d), space O(1)
    public int countDigits(int num) {
        int res = 0, val = num;
        while (val > 0) {
            res += (num % (val % 10) == 0 ? 1 : 0);
            val /= 10;
        }
        return res;
    }
}
