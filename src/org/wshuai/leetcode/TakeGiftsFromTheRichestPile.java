package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 12/17/2023.
 * #2558 https://leetcode.com/problems/take-gifts-from-the-richest-pile/
 */
public class TakeGiftsFromTheRichestPile {

    // time O(n * log(n)), space O(n)
    public long pickGifts(int[] gifts, int k) {
        long res = 0;
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> b - a);
        for (int g : gifts) {
            maxQueue.offer(g);
        }
        while (k-- > 0) {
            int rem = (int) Math.sqrt(maxQueue.poll());
            maxQueue.offer(rem);
        }
        while (!maxQueue.isEmpty()) {
            res += maxQueue.poll();
        }
        return res;
    }
}
