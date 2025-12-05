package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2025.
 * #3461 https://leetcode.com/problems/check-if-digits-are-equal-in-string-after-operations-i/
 */
public class CheckIfDigitsAreEqualInStringAfterOperationsI {

    // time O(n^2), space O(1)
    public boolean hasSameDigits(String s) {
        int[] arr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i) - '0';
        }
        for (int j = s.length() - 1; j >= 2; j--) {
            for (int i = 0; i < j; i++) {
                arr[i] = (arr[i] + arr[i + 1]) % 10;
            }
        }
        return arr[0] == arr[1];
    }
}
