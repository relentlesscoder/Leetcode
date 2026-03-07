package org.wshuai.leetcode.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/25/2023.
 * #2534 https://leetcode.com/problems/time-taken-to-cross-the-door/
 */
public class TimeTakenToCrossTheDoor {

    // time O(n + MAX), space O(n)
    public int[] timeTaken(int[] arrival, int[] state) {
		// 用两个队列分别来模拟进入的队列和离开的队列
        int n = arrival.length, lastUsedTime = -100, lastUsedFor = -1;
        int[] res = new int[n];
        Deque<Integer> enterQueue = new ArrayDeque<>(), exitQueue = new ArrayDeque<>();
		// 极端情况下，所有人都想在 arrival[n - 1] 这个时间进入或者离开。则最晚的那个时间点
		// 不会超过 arrival[n - 1] + n。
		// 遍历所有可能的时间点
        for (int t = arrival[0], p = 0; t <= arrival[n - 1] + n; t++) {
			// 将在当前时间点前想进入/离开的人分别入列
            while (p < n && arrival[p] <= t) {
                if (state[p] == 0) {
                    enterQueue.offer(p);
                } else {
                    exitQueue.offer(p);
                }
                p++;
            }
			// 如果没人想离开则继续
            if (enterQueue.isEmpty() && exitQueue.isEmpty()) {
                continue;
            }
            boolean exitFirst = (lastUsedTime != t - 1 || lastUsedFor == 1);
			// 如果进入的队列为空，或者满足离开优先的条件。则让排在离开队列的第一个人离开，反之
			// 则让排在进入队列的第一个人进入。
            if (enterQueue.isEmpty() || (exitFirst && !exitQueue.isEmpty())) {
                res[exitQueue.poll()] = t;
                lastUsedFor = 1; // 更新上次使用门的目的
            } else {
                res[enterQueue.poll()] = t;
                lastUsedFor = 0; // 更新上次使用门的目的
            }
            lastUsedTime = t; // 更新上次使用门的时间点
        }
        return res;
    }
}
