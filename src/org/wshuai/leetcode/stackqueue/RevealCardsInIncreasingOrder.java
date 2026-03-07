package org.wshuai.leetcode.stackqueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 08/30/2019.
 * #0950 https://leetcode.com/problems/reveal-cards-in-increasing-order/
 */
public class RevealCardsInIncreasingOrder {

    // time O(n), space O(n)
    public int[] deckRevealedIncreasing(int[] deck) {
        // 非常无聊的题，将数组排序后每次把最大的数加到队尾让后把队首的数取出加到队尾。
        // 示例1:
        //   [2,3,5,7,11,13,17]
        // 队列:
        //   [17]
        //   [17,13] -> [13,17]
        //   [13,17,11] -> [17,11,13]
        //   [17,11,13,7] -> [11,13,7,17]
        //   [11,13,7,17,5] -> [13,7,17,5,11]
        //   [13,7,17,5,11,3] -> [7,17,5,11,3,13]
        //   [7,17,5,11,3,13,2]
        int n = deck.length, idx = 0;
        Arrays.sort(deck);
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            queue.offer(deck[i]);
            if (i > 0) {
                queue.offer(queue.poll());
            }
        }
        while (!queue.isEmpty()) {
            deck[idx++] = queue.pollLast();
        }
        return deck;
    }
}
