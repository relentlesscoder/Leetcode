package org.wshuai.leetcode.heap;

import java.util.PriorityQueue;

/**
 * Created by Wei on 01/09/2026.
 * #1801 https://leetcode.com/problems/number-of-orders-in-the-backlog/
 */
public class NumberOfOrdersInTheBacklog {

    private static final int MOD = (int) 1e9 + 7;

    // time O(n * log(n)), space O(n)
    public int getNumberOfBacklogOrders(int[][] orders) {
        long res = 0;
        PriorityQueue<int[]> sellQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> buyQueue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int[] order : orders) {
            int p = order[0], a = order[1], t = order[2];
            if (t == 0) { // 采购订单
                // 匹配积压的销售订单
                while (a > 0 && !sellQueue.isEmpty() && sellQueue.peek()[0] <= p) {
                    int[] curr = sellQueue.peek();
                    int amount = Math.min(curr[1], a);
                    a -= amount;
                    curr[1] -= amount;
                    if (curr[1] == 0) {
                        sellQueue.poll();
                    }
                }
                // 保存未能匹配的采购订单
                if (a > 0) {
                    buyQueue.offer(new int[] {p, a});
                }
            } else { // 销售订单
                // 匹配积压的采购订单
                while (a > 0 && !buyQueue.isEmpty() && buyQueue.peek()[0] >= p) {
                    int[] curr = buyQueue.peek();
                    int amount = Math.min(curr[1], a);
                    a -= amount;
                    curr[1] -= amount;
                    if (curr[1] == 0) {
                        buyQueue.poll();
                    }
                }
                // 保存未能匹配的销售订单
                if (a > 0) {
                    sellQueue.offer(new int[] {p, a});
                }
            }
        }
        while (!buyQueue.isEmpty()) {
            res = (res + buyQueue.poll()[1]) % MOD;
        }
        while (!sellQueue.isEmpty()) {
            res = (res + sellQueue.poll()[1]) % MOD;
        }
        return (int) res;
    }
}
