package org.wshuai.leetcode;

/**
 * Created by Wei on 01/07/2024.
 * #2243 https://leetcode.com/problems/calculate-digit-sum-of-a-string/
 */
public class CalculateDigitSumOfAString {

    // time O(log(n) / log(k)), space O(1)
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;
                for (int j = i; j < s.length() && j < i + k; j++) {
                    sum += s.charAt(j) - '0';
                }
                sb.append(sum);
            }
            s = sb.toString();
        }
        return s;
    }
}
