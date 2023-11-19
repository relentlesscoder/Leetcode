package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/12/2023.
 * #2462 https://leetcode.com/problems/total-cost-to-hire-k-workers/
 */
public class TotalCostToHireKWorkers {

    // time O((m + k) * log(m)), space O(m)
    public long totalCost(int[] costs, int k, int candidates) {
        long res = 0;
        PriorityQueue<Integer> frontQueue = new PriorityQueue<>();
        PriorityQueue<Integer> endQueue = new PriorityQueue<>();
        int n = costs.length, nextHead = 0, nextTail = n - 1;
        for (; nextHead < candidates; nextHead++) {
            frontQueue.offer(costs[nextHead]);
        }
        for (; nextTail > n - 1 - candidates; nextTail--) {
            if (nextTail < nextHead) {
                break;
            }
            endQueue.offer(costs[nextTail]);
        }
        while (k-- > 0) {
            if (endQueue.isEmpty() || (!frontQueue.isEmpty()
                    && frontQueue.peek() <= endQueue.peek())) {
                res += frontQueue.poll();
                if (nextHead <= nextTail) {
                    frontQueue.offer(costs[nextHead++]);
                }
            } else {
                res += endQueue.poll();
                if (nextHead <= nextTail) {
                    endQueue.offer(costs[nextTail--]);
                }
            }
        }
        return res;
    }

    // time O((m + k) * log(m)), space O(m)
    public long totalCostSingleQueue(int[] costs, int k, int candidates) {
        long res = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int n = costs.length, nextHead = 0, nextTail = n - 1;
        for (; nextHead < candidates; nextHead++) {
            queue.offer(new int[] {costs[nextHead], 0});
        }
        for (; nextTail > n - 1 - candidates; nextTail--) {
            if (nextTail < nextHead) {
                break;
            }
            queue.offer(new int[] {costs[nextTail], 1});
        }
        while (k-- > 0) {
            int[] curr = queue.poll();
            res += curr[0];
            if (nextHead <= nextTail) {
                if (curr[1] == 0) {
                    queue.offer(new int[] {costs[nextHead++], 0});
                } else {
                    queue.offer(new int[] {costs[nextTail--], 1});
                }
            }
        }
        return res;
    }
}
