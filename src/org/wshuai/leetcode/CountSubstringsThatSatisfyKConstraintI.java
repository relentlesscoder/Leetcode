package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2025.
 * #3258 https://leetcode.com/problems/count-substrings-that-satisfy-k-constraint-i/
 */
public class CountSubstringsThatSatisfyKConstraintI {

    // time O(n), space O(1)
    public int countKConstraintSubstrings(String s, int k) {
        int res = 0, oneCount = 0, zeroCount = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            oneCount += s.charAt(j) == '1' ? 1 : 0;
            zeroCount += s.charAt(j) == '0' ? 1 : 0;
            while (oneCount > k && zeroCount > k) {
                oneCount -= s.charAt(i) == '1' ? 1 : 0;
                zeroCount -= s.charAt(i) == '0' ? 1 : 0;
                i++;
            }
            res += j - i + 1;
        }
        return res;
    }
}
