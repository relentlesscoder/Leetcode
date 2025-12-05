package org.wshuai.leetcode;

/**
 * Created by Wei on 12/26/2023.
 * #2839 https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-i/
 */
public class CheckIfStringsCanBeMadeEqualWithOperationsI {

    // time O(1), space O(1)
    public boolean canBeEqual(String s1, String s2) {
        String s3 = new String(new char[]{s1.charAt(2), s1.charAt(1), s1.charAt(0), s1.charAt(3)});
        String s4 = new String(new char[]{s1.charAt(0), s1.charAt(3), s1.charAt(2), s1.charAt(1)});
        String s5 = new String(new char[]{s1.charAt(2), s1.charAt(3), s1.charAt(0), s1.charAt(1)});
        return s2.equals(s1) || s2.equals(s3) || s2.equals(s4) || s2.equals(s5);
    }
}
