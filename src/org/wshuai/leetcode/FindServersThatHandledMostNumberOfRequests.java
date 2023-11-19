package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Created by Wei on 10/22/2020.
 * #1606 https://leetcode.com/problems/find-servers-that-handled-most-number-of-requests/
 */
public class FindServersThatHandledMostNumberOfRequests {

	// time O(n*log(n)), space O(n)
	public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
		int n = arrival.length, busiest = 0;
		int[] counter = new int[n];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		TreeSet<Integer> idle = new TreeSet<>();
		for(int i = 0; i < k; i++){
			idle.add(i);
		}
		for(int i = 0; i < n; i++){
			while(!pq.isEmpty() && pq.peek()[0] <= arrival[i]){
				int[] release = pq.poll();
				idle.add(release[1]);
			}
			if(pq.size() == k){
				continue;
			}
			int serverId;
			if(idle.ceiling(i % k) != null){
				serverId = idle.ceiling(i % k);
			}else{
				serverId = idle.ceiling(0);
			}
			idle.remove(serverId);
			pq.offer(new int[]{arrival[i] + load[i], serverId});
			busiest = Math.max(busiest, ++counter[serverId]);
		}
		List<Integer> res = new ArrayList<>();
		for(int i = 0; i < n; i++){
			if(counter[i] == busiest){
				res.add(i);
			}
		}
		return res;
	}
}
