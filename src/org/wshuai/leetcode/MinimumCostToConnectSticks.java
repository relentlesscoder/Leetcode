package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/19/2019.
 * #1167 https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 */
public class MinimumCostToConnectSticks {

    // time O(n * log(n)), space O(n)
    public int connectSticks(int[] sticks) {
		// 贪心，证明: https://leetcode.cn/problems/minimum-cost-to-connect-sticks/solutions/186427/lian-jie-bang-cai-de-zui-di-fei-yong-by-leetcode-s/
        int res = 0;
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        for (int s : sticks) {
            minQueue.offer(s);
        }
        while (minQueue.size() > 1) {
            int x = minQueue.poll(), y = minQueue.poll();
            res += x + y;
            minQueue.offer(x + y);
        }
        return res;
    }
}
