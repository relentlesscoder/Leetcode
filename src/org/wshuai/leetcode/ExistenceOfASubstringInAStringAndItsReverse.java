package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2025.
 * #3083 https://leetcode.com/problems/existence-of-a-substring-in-a-string-and-its-reverse/
 */
public class ExistenceOfASubstringInAStringAndItsReverse {

    // time O(n), space O(3000)
    public boolean isSubstringPresent(String s) {
        int n = s.length();
        int[] map = new int[3000];
        for (int i = 1; i < n; i++) {
            int prev = s.charAt(i - 1) - 'a', curr = s.charAt(i) - 'a';
            map[prev * 100 + curr]++;
        }
        for (int i = n - 2; i >= 0; i--) {
            int prev = s.charAt(i + 1) - 'a', curr = s.charAt(i) - 'a';
            if (map[prev * 100 + curr] > 0) {
                return true;
            }
        }
        return false;
    }
}
