package org.wshuai.leetcode;

/**
 * Created by Wei on 11/22/2023.
 * #2586 https://leetcode.com/problems/count-the-number-of-vowel-strings-in-range/
 */
public class CountTheNumberOfVowelStringsInRange {

    // time O(n), space O(1)
    public int vowelStrings(String[] words, int left, int right) {
        int res = 0;
        for (int i = left; i <= right; i++) {
            if (isVowelCharacter(words[i].charAt(0)) && isVowelCharacter(words[i].charAt(words[i].length() - 1))) {
                res++;
            }
        }
        return res;
    }

    private boolean isVowelCharacter(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
