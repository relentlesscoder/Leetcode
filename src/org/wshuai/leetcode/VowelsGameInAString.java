package org.wshuai.leetcode;

/**
 * Created by Wei on 09/20/2025.
 * #3227 https://leetcode.com/problems/vowels-game-in-a-string/
 */
public class VowelsGameInAString {

    // time O(n), space O(1)
    public boolean doesAliceWin(String s) {
        // 1. If there are no vowels in the initial string, then Bob wins.
        // 2. If the number of vowels in the initial string is odd,
        // then Alice can remove the whole string on her first turn and win.
        // 3. What if the number of vowels in the initial string is even, Alice
        // leave only 1 vowel by removing all others, then by #2 she will win.
        for (char c : s.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return true;
            }
        }
        return false;
    }
}
