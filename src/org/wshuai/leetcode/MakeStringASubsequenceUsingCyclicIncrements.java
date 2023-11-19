package org.wshuai.leetcode;

/**
 * Created by Wei on 09/07/2023.
 * #2825 https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments/
 */
public class MakeStringASubsequenceUsingCyclicIncrements {

    // time O(m + n), space O(1)
    public boolean canMakeSubsequence(String str1, String str2) {
        int m = str1.length(), n = str2.length(), i = 0, j = 0;
        if (n > m) {
            return false;
        }
        while (i < m && j < n) {
            char c1 = str1.charAt(i), c2 = str2.charAt(j);
            if (c1 == c2 || getCyclicallyNext(c1) == c2) {
                j++;
            }
            i++;
        }
        return j == n;
    }

    private char getCyclicallyNext(char c) {
        return c == 'z' ? 'a' : (char)(c + 1);
    }
}
