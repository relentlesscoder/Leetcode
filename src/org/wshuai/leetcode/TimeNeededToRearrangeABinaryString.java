package org.wshuai.leetcode;

/**
 * Created by Wei on 01/11/2024.
 * #2380 https://leetcode.com/problems/time-needed-to-rearrange-a-binary-string/
 */
public class TimeNeededToRearrangeABinaryString {

    // time O(n), space O(1)
    public int secondsToRemoveOccurrences(String s) {
        int seconds = 0, zeros = 0;
        for (int i = 0; i < s.length(); i++) {
            zeros += s.charAt(i) == '0' ? 1 : 0;
            if (s.charAt(i) == '1' && zeros > 0) {
                // If zeros are greater than seconds + 1, then the current 1 needs to move over all the zeros.
                // We can see the difference by two examples:
                // 1. the '1' at index 7 of "00011001101"
                // 2. the '1' at index 6 of "01000011001"
                seconds = Math.max(seconds + 1, zeros);
            }
        }
        return seconds;
    }
}
