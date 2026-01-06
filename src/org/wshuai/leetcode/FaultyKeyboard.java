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
        // 不用真的每次翻转字符串，而是用一个变量 direction 表示当前的方向 - 1 表示从左
        // 到右而 0 表示从右到左。
        int n = s.length(), direction = 1;
        Deque<Character> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'i') {
                direction ^= 1; // 改变当前方向
            } else if (direction == 1) { // 根据当前方向决定把字符加到双端队列的哪一边
                queue.offer(c);
            } else {
                queue.offerFirst(c);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            // 根据最终方向决定顺序
            res.append(direction == 1 ? queue.poll() : queue.pollLast());
        }
        return res.toString();
    }
}
