package org.wshuai.leetcode;

/**
 * Created by Wei on 09/04/2023.
 * #2259 https://leetcode.com/problems/remove-digit-from-number-to-maximize-result/description/
 */
public class RemoveDigitFromNumberToMaximizeResult {

    // time O(n), space O(1)
    public String removeDigit(String number, char digit) {
        int lastSeen = -1;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == digit) {
                lastSeen = i;
                if (i + 1 < number.length() && number.charAt(i + 1) > number.charAt(i)) {
                    return number.substring(0, i) + number.substring(i + 1);
                }
            }
        }
        return number.substring(0, lastSeen) + number.substring(lastSeen + 1);
    }
}
