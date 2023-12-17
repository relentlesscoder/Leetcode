package org.wshuai.leetcode;

/**
 * Created by Wei on 12/17/2023.
 * #2937 https://leetcode.com/problems/make-three-strings-equal/
 */
public class MakeThreeStringsEqual {

    // time O(n), space O(1)
    public int findMinimumOperations(String s1, String s2, String s3) {
        int commonPrefix = 0;
        for (int i = 0; i < s1.length() && i < s2.length() && i < s3.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i) && s2.charAt(i) == s3.charAt(i)) {
                commonPrefix++;
            } else {
                break;
            }
        }
        return commonPrefix == 0 ? -1 : s1.length() + s2.length() + s3.length() - commonPrefix * 3;
    }
}
