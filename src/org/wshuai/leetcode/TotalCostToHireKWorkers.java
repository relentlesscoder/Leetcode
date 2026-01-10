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
        int n = costs.length, i = 0, j = n - 1;
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) ->
                a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        // 加入最前面 candidates 个工人
        for (; i < candidates; i++) { // O(m)
            minQueue.offer(new int[]{costs[i], 0});
        }
        // 加入最后面 candidates 个工人 (如果后面还有足够的工人)
        for (; j >= i && j > n - 1 - candidates; j--) { // O(m)
            minQueue.offer(new int[]{costs[j], 1});
        }
        while (k-- > 0) { // O(k)
            int[] top = minQueue.poll(); // 找到当前最小代价的工人
            res += top[0]; // 更新答案
            if (i <= j) {
                if (top[1] == 0) { // 如果是从左边选的则从左边加入一个工人
                    minQueue.offer(new int[]{costs[i++], 0});
                } else { // 如果是从右边选的则从右边加入一个工人
                    minQueue.offer(new int[]{costs[j--], 1});
                }
            }
        }
        return res;
    }
}
