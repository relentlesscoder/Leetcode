package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/12/2019.
 * #0621 https://leetcode.com/problems/task-scheduler/
 */
public class TaskScheduler {

	// time O(n)
	public int leastInterval(char[] tasks, int n) {
		int res = 0;
		int[] count = new int[26];
		for(char c : tasks){
			count[c - 'A']++;
		}
		Arrays.sort(count);
		int i = 25;
		while(i >= 0 && count[i] == count[25]){
			i--;
		}
		return Math.max(tasks.length, (count[25] - 1) * (n + 1) + 25 - i);
	}

	/*why "...when the first k - 1 chunks cannot hold all tasks, why the result will be tasks.length...":

	before filling tasks in chunk_j where 0 <= j <= k-1, the # of tasks in chunks before chunk_j is exactly one more than
	the # of tasks in chunk_j and all the chunks after chunk_j. This is guaranteed by "append ... sequentially and round
	and round", so when one of the chunks burst / cannot hold more tasks with the initial n slots, all the chunks are full.
	based on 1), when first k-1 chunks cannot hold all tasks, they are all of size (n+1), at this point, and the remaining
	tasks are guaranteed to have frequency less than or equal to k-1, so as long as you keep on appending the task to these
	k-1 chunks one at a time, there will be at most one task of the same type in any particular chunk, in other words, the
	same type of tasks will be spaced out at least by n slots.
	based on 2), after all k-1 chunks burst, no idle slots would be necessary for us to append rest of the tasks, hence the
	result tasks.length*/

	// time O(d*n), d = tasks.length
	public int leastIntervalCounting(char[] tasks, int n) {
		int res = 0;
		int[] count = new int[26];
		for(char c : tasks){
			count[c - 'A']++;
		}
		Arrays.sort(count);
		while(count[25] > 0){
			// one cpu cycle
			for(int i = 0; i <= n; i++){
				if(count[25] == 0){ // handle the last cycle
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
