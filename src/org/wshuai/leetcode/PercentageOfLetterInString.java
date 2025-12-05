package org.wshuai.leetcode;

/**
 * Created by Wei on 11/22/2023.
 * #2278 https://leetcode.com/problems/percentage-of-letter-in-string/
 */
public class PercentageOfLetterInString {

    // time O(n), space O(1)
    public int percentageLetter(String s, char letter) {
        double count = 0.0, total = 0.0;
        for (char c : s.toCharArray()) {
            if (c == letter) {
                count += 1.0;
            }
            total += 1.0;
        }
        return (int)(count / total * 100);
    }
}
