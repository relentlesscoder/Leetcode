package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2025.
 * #3114 https://leetcode.com/problems/latest-time-you-can-obtain-after-replacing-characters/
 */
public class LatestTimeYouCanObtainAfterReplacingCharacters {

    // time O(1), space O(1)
    public String findLatestTime(String s) {
        char[] arr = new char[] {'1', '1', ':', '5', '9'};
        if (s.charAt(0) != '?') {
            arr[0] = s.charAt(0);
        } else if (s.charAt(1) >= '2' && s.charAt(1) <= '9') {
            arr[0] = '0';
        }
        if (s.charAt(1) != '?') {
            arr[1] = s.charAt(1);
        } else if (s.charAt(0) == '0') {
            arr[1] = '9';
        }
        if (s.charAt(3) != '?') {
            arr[3] = s.charAt(3);
        }
        if (s.charAt(4) != '?') {
            arr[4] = s.charAt(4);
        }
        return String.valueOf(arr);
    }
}
