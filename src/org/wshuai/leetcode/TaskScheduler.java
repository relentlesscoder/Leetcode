package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/12/2019.
 * #0621 https://leetcode.com/problems/task-scheduler/
 */
public class TaskScheduler {

	public int leastIntervalSorting(char[] tasks, int n) {
		int[] count = new int[26];
		for (char c : tasks) {
			count[c - 'A']++;
		}
		Arrays.sort(count);
		int time = 0;
		while (count[25] > 0) {
			int i = 0;
			while (i <= n) {
				// tricky - if the max count of character is 1 then
				// just write the character one by one, otherwise
				// write n + 1 characters each time
				if (count[25] == 0) {
					break;
				}
				if (i < 26 && count[25 - i] > 0) {
					count[25 - i]--;
				}
				time++;
				i++;
			}
			Arrays.sort(count);
		}
		return time;
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
