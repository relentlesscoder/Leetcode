package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 11/18/2023.
 * #2828 https://leetcode.com/problems/check-if-a-string-is-an-acronym-of-words/
 */
public class CheckIfAStringIsAnAcronymOfWords {

    // time O(n), space O(1)
    public boolean isAcronym(List<String> words, String s) {
        if (words.size() != s.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != words.get(i).charAt(0)) {
                return false;
            }
        }
        return true;
    }
}
