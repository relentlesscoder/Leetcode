package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/23/2023.
 * #1937 https://leetcode.com/problems/maximum-number-of-points-with-cost/description/
 */
public class ProcessTasksUsingServers {

	// time O((m + n) * log(n)), space O(n)
	public int[] assignTasks(int[] servers, int[] tasks) {
		int n = servers.length, m = tasks.length;
		PriorityQueue<int[]> idleQueue = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		PriorityQueue<int[]> taskQueue = new PriorityQueue<>((a, b) -> (a[2] != b[2]) ? (a[2] - b[2]) : ((a[0] != b[0]) ? (a[0] - b[0]) : (a[1] - b[1])));
		for (int i = 0; i < n; i++) { // initially we add all servers to the idle queue
			idleQueue.offer(new int[] {servers[i], i, 0});
		}
		int[] res = new int[m];
		int taskIndex = 0, time = 0;
		while (taskIndex < m) {
			while (!taskQueue.isEmpty() && taskQueue.peek()[2] <= time) {
				idleQueue.offer(taskQueue.poll());
			}
			if (idleQueue.size() == 0) {
				time = taskQueue.peek()[2]; // if no servers are free, advance time to next time when there are free servers
			} else {
				// assign available tasks (can assign multiple tasks at the same time) by insertion order if there are free servers
				while (!idleQueue.isEmpty() && time >= taskIndex && taskIndex < m) {
					int[] next = idleQueue.poll();
					next[2] = time + tasks[taskIndex];
					taskQueue.offer(next);
					res[taskIndex++] = next[1];
				}
				time++;
			}
		}
		return res;
	}
}
