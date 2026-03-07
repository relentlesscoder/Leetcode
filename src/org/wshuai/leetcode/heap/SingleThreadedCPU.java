package org.wshuai.leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/25/2023.
 * #1834 https://leetcode.com/problems/single-threaded-cpu/
 */
public class SingleThreadedCPU {

    // time O(n * log(n)), space O(n)
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        // 把索引按进入任务队列时间排序
        Integer[] sorted = new Integer[n];
        Arrays.setAll(sorted, i -> i);
        Arrays.sort(sorted, (a, b) -> tasks[a][0] - tasks[b][0]);
        // 维护一个最小队列，按任务时间和其在任务数组中的原索引排序
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) ->
                a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] res = new int[n];
        for (int idx = 0, j = 0, start = 0; idx < n; ) {
            if (!minQueue.isEmpty()) { // 如果队列中有任务
                int[] curr = minQueue.poll();
                res[idx++] = curr[1]; // 弹出队首的任务并执行
                start += curr[0]; // 将下一个任务的开始时间设为当前任务结束时间
            } else {
                // 注意如果当前任务结束时间之前下一个任务还没有开始则需要将下一个任
                // 务的开始时间设为排序后下一个索引的开始时间，这种情况 cpu 会有一
                // 段空闲状态。
                start = tasks[sorted[j]][0];
            }
            // 将所有在当前任务结束时间前开始的任务加入队列
            while (j < n && tasks[sorted[j]][0] <= start) {
                minQueue.offer(new int[]{tasks[sorted[j]][1], sorted[j]});
                j++;
            }
        }
        return res;
    }
}
