package org.wshuai.leetcode;

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
            if (!canAssign(tasks, workers, pills, strength, mid)) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private boolean canAssign(int[] tasks, int[] workers, int pills, int strength, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int i = 0;
        // Greedy: use the k strongest workers to complete the k easiest works
        for (int j = workers.length - k; j < workers.length; j++) {
            int w = workers[j];
            // For current worker, add all tasks he can complete (with pill)
            while (i < k && tasks[i] <= w + strength) {
                queue.offer(tasks[i]);
                i++;
            }
            // Impossible to complete any work
            if (queue.isEmpty()) {
                return false;
            }
            // Complete the easiest work without pill, note that the worker can also
            // pick another harder work (LOE to his strength) but that does not offer
            // any benefit because stronger worker can also complete the job later
            if (w >= queue.peek()) {
                queue.poll();
                continue;
            }
            // Return false if current worker can't even complete the easiest work
            // and there is no pills left
            if (pills == 0) {
                return false;
            }
            // If a pill needs to be used, then complete the hardest work to maximize
            // the profit. This is to increase the chance a stronger worker can pick
            // an easier job without using a pill
            pills--;
            queue.pollLast();
        }
        return true;
    }
}
