package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/12/2019.
 * #0621 https://leetcode.com/problems/task-scheduler/
 */
public class TaskScheduler {

	// time O(d*n), d = tasks.length
	public int leastInterval(char[] tasks, int n) {
		int res = 0;
		int[] count = new int[26];
		for(char c : tasks){
			count[c - 'A']++;
		}
		Arrays.sort(count);
		while(count[25] > 0){
			// one cpu cycle
			for(int i = 0; i <= n; i++){
				// end immediately when all tasks are done
				if(count[25] == 0){
					break;
				}
				// add any pending tasks
				if(i < 26 && count[25 - i] > 0){
					count[25 - i]--;
				}
				res++;
			}
			Arrays.sort(count);
		}
		return res;
	}

	public int leastIntervalPriorityQueue(char[] tasks, int n) {
		if (n == 0) {
			return tasks.length;
		}
		int res = 0;
		// time O(l)
		int[] map = new int[26];
		for (char c : tasks) {
			map[c - 'A']++;
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		// time O(d*log(d))
		for (int i = 0; i < 26; i++) {
			if (map[i] > 0) {
				pq.offer(new int[]{i, map[i]});
			}
		}
		// time O(n*log(d))
		while (!pq.isEmpty()) {
			List<int[]> next = new ArrayList<>();
			int i = 0;
			for (; i <= n && !pq.isEmpty(); i++) {
				int[] cur = pq.poll();
				if (cur[1] > 1) {
					cur[1]--;
					next.add(cur);
				}
			}
			if (next.size() > 0) {
				res += n + 1;
				for (int[] arr : next) {
					pq.offer(arr);
				}
			} else {
				res += i;
			}
		}
		return res;
	}
}
