package org.wshuai.leetcode;

/**
 * Created by Wei on 01/06/2024.
 * #1945 https://leetcode.com/problems/sum-of-digits-of-string-after-convert/
 */
public class SumOfDigitsOfStringAfterConvert {

    // time O(k * log(n)), space O(1)
    public int getLucky(String s, int k) {
        int sum = 0;
        for (char c : s.toCharArray()) {
            int val = c - 'a' + 1;
            sum += val % 10 + val / 10;
        }
        while (k-- > 1) {
            int temp = 0;
            while (sum > 0) {
                temp += sum % 10;
                sum /= 10;
            }
            sum = temp;
        }
        return sum;
    }
}
