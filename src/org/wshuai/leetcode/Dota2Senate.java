package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/12/2019.
 * #0649 https://leetcode.com/problems/dota2-senate/
 */
public class Dota2Senate {

    // time O(n), space O(n)
    public String predictPartyVictory(String senate) {
		// 遍历数组分别将 R 和 D 的索引加入两个队列，每次比较两个队列的队首两数将将较大的那个数
		// 永久移除而将较小的那个数加 n 放入队尾。这样做的原因是，拥有较小索引的那个参议员会优先
		// 将较大的那个参议院 (对方阵营中最早可以投票的参议员) 的投票权禁掉而防治他禁掉己方参议员
		// 的投票权。加 n 是因为要确保这个参议员只能在下一轮再次投票。
        int n = senate.length();
        Deque<Integer> radiants = new ArrayDeque<>();
        Deque<Integer> dires = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                radiants.offer(i);
            } else {
                dires.offer(i);
            }
        }
        while (!radiants.isEmpty() && !dires.isEmpty()) {
            int r = radiants.poll(), d = dires.poll();
            if (r < d) {
                radiants.offer(r + n);
            } else {
                dires.offer(d + n);
            }
        }
        return radiants.isEmpty() ? "Dire" : "Radiant";
    }
}
