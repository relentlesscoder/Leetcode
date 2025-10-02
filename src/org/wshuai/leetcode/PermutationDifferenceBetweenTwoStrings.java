package org.wshuai.leetcode;

/**
 * Created by Wei on 10/01/2025.
 * #3146 https://leetcode.com/problems/permutation-difference-between-two-strings/
 */
public class PermutationDifferenceBetweenTwoStrings {

    // time O(n), space O(1)
    public int findPermutationDifference(String s, String t) {
        int res = 0;
        int[] index = new int[26];
        for (int i = 0; i < s.length(); i++) {
            index[s.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < s.length(); i++) {
            res += Math.abs(i - index[t.charAt(i) - 'a']);
        }
        return res;
    }
}
