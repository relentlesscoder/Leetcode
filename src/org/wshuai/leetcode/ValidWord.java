package org.wshuai.leetcode;

/**
 * Created by Wei on 06/23/2025.
 * #3136 https://leetcode.com/problems/valid-word/
 */
public class ValidWord {

    // time O(n), space O(1)
    public boolean isValid(String word) {
        if (word.length() < 3) {
            return false;
        }
        boolean vowelSeen = false, consonantSeen = false;
        for (char c : word.toCharArray()) {
            if (c >= '0' && c <= '9') {
                continue;
            } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                char l = Character.toLowerCase(c);
                if (l == 'a' || l == 'e' || l == 'i' || l == 'o' || l == 'u') {
                    vowelSeen = true;
                } else {
                    consonantSeen = true;
                }
            } else {
                return false;
            }
        }
        return vowelSeen && consonantSeen;
    }
}
