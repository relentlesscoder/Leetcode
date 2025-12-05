package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 01/10/2024.
 * #2335 https://leetcode.com/problems/minimum-amount-of-time-to-fill-cups/
 */
public class MinimumAmountOfTimeToFillCups {

    // time O(n * log(n)), space O(n)
    public int fillCups(int[] amount) {
        int res = 0;
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < 3; i++) {
            if (amount[i] == 0) {
                continue;
            }
            maxQueue.offer(amount[i]);
        }
        while (maxQueue.size() > 1) {
            int a = maxQueue.poll(), b = maxQueue.poll();
            if (--a > 0) {
                maxQueue.offer(a);
            }
            if (--b > 0) {
                maxQueue.offer(b);
            }
            res++;
        }
        if (!maxQueue.isEmpty()) {
            res += maxQueue.peek();
        }
        return res;
    }
}
