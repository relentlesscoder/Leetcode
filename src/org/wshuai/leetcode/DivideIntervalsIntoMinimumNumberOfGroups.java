package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/23/2023.
 * #2406 https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/
 */
public class DivideIntervalsIntoMinimumNumberOfGroups {

    // time O(n), space O(m)
    public int minGroups(int[][] intervals) {
        int res = 0, max = 0, groups = 0;
        for (int[] in : intervals) {
            max = Math.max(max, in[1]);
        }
        int[] diff = new int[max + 2];
        for (int[] in : intervals) {
            diff[in[0]]++;
            diff[in[1] + 1]--;
        }
        for (int i = 1; i <= max; i++) {
            groups += diff[i];
            res = Math.max(res, groups);
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public int minGroupsPriorityQueue(int[][] intervals) {
        // 将区间按左端点排序
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        // 维护一个区间右端点的最小堆
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        for (int[] interval : intervals) {
            // 贪心: 如果当前区间的左端点和最小堆中最小的右端点不重合则可以将当前区间加入与它相同
            // 的区间组中。否则需要为当前区间新创建一个组。
            if (!minQueue.isEmpty() && minQueue.peek() < interval[0]) {
                minQueue.poll();
            }
            // 将当前区间右端点入列
            minQueue.offer(interval[1]);
        }
        return minQueue.size();
    }
}
