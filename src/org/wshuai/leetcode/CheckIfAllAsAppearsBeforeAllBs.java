package org.wshuai.leetcode;

/**
 * Created by Wei on 12/26/2023.
 * #2124 https://leetcode.com/problems/check-if-all-as-appears-before-all-bs/
 */
public class CheckIfAllAsAppearsBeforeAllBs {

    // time O(n), space O(1)
    public boolean checkString(String s) {
        int left = 0, right = s.length() - 1;
        while (left < s.length() && s.charAt(left) == 'a') {
            left++;
        }
        while (right >= 0 && s.charAt(right) == 'b') {
            right--;
        }
        return left - right == 1;
    }
}
