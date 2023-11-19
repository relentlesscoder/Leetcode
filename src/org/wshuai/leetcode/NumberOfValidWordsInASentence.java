package org.wshuai.leetcode;

/**
 * Created by Wei on 09/03/2023.
 * #2047 https://leetcode.com/problems/number-of-valid-words-in-a-sentence/description/
 */
public class NumberOfValidWordsInASentence {

    // time O(n), space O(1)
    public int countValidWords(String sentence) {
        int valid = 0;
        String[] tokens = sentence.trim().split("\\s+");
        for (String token : tokens) {
            valid += isValidToken(token) ? 1 : 0;
        }
        return valid;
    }

    private boolean isValidToken(String token) {
        boolean hypensSeen = false;
        for (int i = 0; i < token.length() - 1; i++) {
            char c = token.charAt(i);
            if (c == '-') {
                if (i == 0 || hypensSeen || !isLowerCase(token.charAt(i + 1))) { // corner case: check if hyphen is surrounded by lowercase characters
                    return false;
                }
                hypensSeen = true;
            } else if (!isLowerCase(c)) {
                return false;
            }
        }
        char last = token.charAt(token.length() - 1);
        return last == '!' || last == '.' || last == ',' || isLowerCase(last);
    }

    private boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }
}
