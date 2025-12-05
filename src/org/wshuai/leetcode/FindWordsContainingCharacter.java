package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/23/2023.
 * #2942 https://leetcode.com/problems/find-words-containing-character/
 */
public class FindWordsContainingCharacter {

    // time O(n), space O(1)
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                if (c == x) {
                    res.add(i);
                    break;
                }
            }
        }
        return res;
    }
}
