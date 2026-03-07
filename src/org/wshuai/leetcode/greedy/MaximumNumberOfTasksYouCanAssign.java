package org.wshuai.leetcode.greedy;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Wei on 10/09/2025.
 * #2071 https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign/
 */
public class MaximumNumberOfTasksYouCanAssign {

    // time O(n * log(n) + m * log(m)), space O(min(m, n))
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int left = 0, right = Math.min(tasks.length, workers.length);
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (!canCompleteArray(tasks, workers, pills, strength, mid)) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private boolean canCompleteArray(int[] tasks, int[] workers, int pills, int strength, int k) {
        // 用数组取代双端数列
        int[] queue = new int[tasks.length];
        for (int i = workers.length - k, j = 0, head = 0, tail = 0; i < workers.length; i++) {
            while (j < tasks.length && workers[i] + strength >= tasks[j]) {
                queue[tail++] = tasks[j++];
            }
            if (head == tail) {
                return false;
            }
            if (workers[i] >= queue[head]) {
                head++;
                continue;
            }
            if (pills == 0) {
                return false;
            }
            pills--;
            tail--;
        }
        return true;
    }

    private boolean canCompleteQueue(int[] tasks, int[] workers, int pills, int strength, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int i = 0;
        // 贪心: 用最强的 k 个工人去完成最简单的 k 个任务。
        for (int j = workers.length - k; j < workers.length; j++) {
            int w = workers[j];
            // 把所有可以被当前工人完成的任务 (包括使用或者不使用药丸的情况) 加到队列中。
            while (i < k && tasks[i] <= w + strength) {
                queue.offer(tasks[i]);
                i++;
            }
            // 直接返回如果不能完成任何任务
            if (queue.isEmpty()) {
                return false;
            }
            // 如果能不用药丸就能完成最简单的任务则选择最简单的任务。注意这里他可以做更难的任务
            // 但是那样无法得到任何好处还会降低后面的工人完成任务的可能性。
            if (w >= queue.peek()) {
                queue.poll();
                continue;
            }
            // 直接返回如果当前工人即使用药也无法完成最简单的任务
            if (pills == 0) {
                return false;
            }
            // 如果必须使用一颗药丸则选择最难的任务，这样可以增加后面的工人不使用药丸完成最简单
            // 的任务和使用药丸完成比当前任务更简单的任务的可能性。
            pills--;
            queue.pollLast();
        }
        return true;
    }
}
