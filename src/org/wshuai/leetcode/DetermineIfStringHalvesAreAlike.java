package org.wshuai.leetcode;

/**
 * Created by Wei on 12/28/2020.
 * #1704 https://leetcode.com/problems/determine-if-string-halves-are-alike/
 */
public class DetermineIfStringHalvesAreAlike {

    // time O(n)
    public boolean halvesAreAlike(String s) {
        int vowels = 0, half = s.length() >> 1;
        for (int i = 0; i < half; i++) {
            if (isVowel(s.charAt(i))) {
                vowels++;
            }
        }
        for (int i = half; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) {
                vowels--;
            }
        }
        return vowels == 0;
    }

    private boolean isVowel(char c) {
        return c == 'a'
                || c == 'e'
                || c == 'i'
                || c == 'o'
                || c == 'u'
                || c == 'A'
                || c == 'E'
                || c == 'I'
                || c == 'O'
                || c == 'U';
    }
}
