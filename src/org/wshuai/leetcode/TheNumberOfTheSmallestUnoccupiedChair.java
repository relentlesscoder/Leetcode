package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 01/10/2024.
 * #1942 https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/
 */
public class TheNumberOfTheSmallestUnoccupiedChair {

    // time O(n * log(n)), space O(n)
    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length, max = 0, arrival = times[targetFriend][0];
        // 按到达时间排序
        Arrays.sort(times, (a, b) -> a[0] - b[0]); // O(n * log(n))
        // 维护一个以离开时间排序的最小堆 reserved - 表示被朋友占据的椅子的离开时间 + 椅子编号
        PriorityQueue<int[]> reserved = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // 维护一个以椅子编号排序的最小堆 available - 表示朋友离开后空出的椅子
        PriorityQueue<Integer> available = new PriorityQueue<>();
        for (int i = 0; i < n; i++) { // O(n)
            int a = times[i][0], l = times[i][1];
            // 将所有小于等于当前到达时间椅子弹出 reserved 队列并加入到 available 队列
            while (!reserved.isEmpty() && reserved.peek()[0] <= a) {
                available.offer(reserved.poll()[1]);
            }
            // 如果已经有空的椅子则占据最小编号的空椅子，否则需要拿到最大编号的椅子。
            int seat = !available.isEmpty() ? available.poll() : max++;
            if (a == arrival) { // 目标朋友已到达
                return seat;
            }
            // 占据椅子
            reserved.offer(new int[] {l, seat});
        }
        return -1;
    }
}
