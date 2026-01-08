package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 08/21/2019.
 * #1046 https://leetcode.com/problems/last-stone-weight/
 */
public class LastStoneWeight {

    // time O(n * log(n)), space O(n)
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> b - a);
        for (int s : stones) {
            maxQueue.offer(s);
        }
        while (maxQueue.size() > 1) {
            int y = maxQueue.poll(), x = maxQueue.poll();
            if (x != y) {
                maxQueue.offer(y - x);
            }
        }
        return maxQueue.isEmpty() ? 0 : maxQueue.peek();
    }
}
