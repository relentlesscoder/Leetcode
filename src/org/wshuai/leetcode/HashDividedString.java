package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2025.
 * #3271 https://leetcode.com/problems/hash-divided-string/
 */
public class HashDividedString {

    // time O(n), space O(n/k)
    public String stringHash(String s, int k) {
        StringBuilder res = new StringBuilder();
        for (int i = 0, count = 0, sum = 0; i < s.length(); i++) {
            sum += (s.charAt(i) - 'a');
            if (++count == k) {
                res.append((char)('a' + sum % 26));
                count = 0;
                sum = 0;
            }
        }
        return res.toString();
    }
}
