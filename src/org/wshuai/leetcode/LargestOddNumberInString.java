package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2023.
 * #1903 https://leetcode.com/problems/largest-odd-number-in-string/description/
 */
public class LargestOddNumberInString {

    // time O(n), space O(1)
    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            int digit = num.charAt(i) - '0';
            if (digit % 2 == 1) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }
}
