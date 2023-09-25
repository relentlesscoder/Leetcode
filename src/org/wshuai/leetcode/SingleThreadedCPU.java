package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/25/2023.
 * #1834 https://leetcode.com/problems/single-threaded-cpu/
 */
public class SingleThreadedCPU {

	// time O(n * log(n)), space O(n)
	public int[] getOrder(int[][] tasks) {
		int n = tasks.length, time = 0, taskIndex = 0, processIndex = 0;
		int[] processOrder = new int[n];
		Integer[] taskIndexOrder = new Integer[n];
		for (int i = 0; i < n; i++) {
			taskIndexOrder[i] = i;
		}
		Arrays.sort(taskIndexOrder, (a, b) -> tasks[a][0] - tasks[b][0]);
		PriorityQueue<int[]> taskPools = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
		while (processIndex < n) {
			while (taskIndex < n && tasks[taskIndexOrder[taskIndex]][0] <= time) { // add all available tasks to the task pool
				taskPools.offer(new int[] {tasks[taskIndexOrder[taskIndex]][1], taskIndexOrder[taskIndex]});
				taskIndex++;
			}
			if (!taskPools.isEmpty()) { // pick the next task to execute
				int[] next = taskPools.poll();
				processOrder[processIndex++] = next[1];
				time += next[0];
			} else {
				time = tasks[taskIndexOrder[taskIndex]][0]; // no tasks are available, advance to the next enqueue time
			}
		}
		return processOrder;
	}
}
