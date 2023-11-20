package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/19/2023.
 * #2810 https://leetcode.com/problems/faulty-keyboard/
 */
public class FaultyKeyboard {

    // time O(n), space O(n)
    public String finalString(String s) {
        boolean flip = false;
        Deque<Character> queue = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == 'i') {
                flip = !flip;
                continue;
            }
            if (flip) {
                queue.offerFirst(c);
            } else {
                queue.offerLast(c);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            res.append(flip ? queue.pollLast() : queue.pollFirst());
        }
        return res.toString();
    }
}
