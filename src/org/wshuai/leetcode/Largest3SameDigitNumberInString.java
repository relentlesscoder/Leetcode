package org.wshuai.leetcode;

/**
 * Created by Wei on 12/28/2023.
 * #2264 https://leetcode.com/problems/largest-3-same-digit-number-in-string/
 */
public class Largest3SameDigitNumberInString {

    // time O(n), space O(1)
    public String largestGoodInteger(String num) {
        char max = ' ';
        for (int i = 2; i < num.length(); i++) {
            if (num.charAt(i) == num.charAt(i - 1) && num.charAt(i) == num.charAt(i - 2) && num.charAt(i) > max) {
                max = num.charAt(i);
            }
        }
        return max == ' ' ? "" : new String(new char[]{max, max, max});
    }
}
