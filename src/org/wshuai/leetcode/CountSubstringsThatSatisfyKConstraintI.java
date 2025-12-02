package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2025.
 * #3258 https://leetcode.com/problems/count-substrings-that-satisfy-k-constraint-i/
 */
public class CountSubstringsThatSatisfyKConstraintI {

    // time O(n), space O(1)
    public int countKConstraintSubstrings(String s, int k) {
        int res = 0, n = s.length();
        for (int i = 0, j = 0, zeros = 0, ones = 0; i < n; i++) {
            int curr = s.charAt(i) - '0';
            zeros += 1 - curr;
            ones += curr;
            while (zeros > k && ones > k) {
                int head = s.charAt(j++) - '0';
                zeros -= 1 - head;
                ones -= head;
            }
            res += i - j + 1;
        }
        return res;
    }
}
