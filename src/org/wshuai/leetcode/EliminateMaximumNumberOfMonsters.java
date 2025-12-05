package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 12/29/2023.
 * #1921 https://leetcode.com/problems/eliminate-maximum-number-of-monsters/
 */
public class EliminateMaximumNumberOfMonsters {

    // time O(n * log(n)), space O(n)
    public int eliminateMaximum(int[] dist, int[] speed) {
        int res = 0, n = dist.length, time = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) { // enqueue the required time for each monster to arrive
            queue.offer((dist[i] + speed[i] - 1) / speed[i]);
        }
        while (!queue.isEmpty() && queue.peek() > time) {
            queue.poll();
            res++;
            time++;
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public int eliminateMaximumSorting(int[] dist, int[] speed) {
        int res = 0, n = dist.length;
        for (int i = 0; i < n; i++) {
            dist[i] = (dist[i] + speed[i] - 1) / speed[i];
        }
        Arrays.sort(dist);
        for (int i = 0; i < n; i++) {
            if (dist[i] <= i) {
                break;
            }
            res++;
        }
        return res;
    }
}
