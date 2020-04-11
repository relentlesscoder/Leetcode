package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 03/16/2020.
 * #1383 https://leetcode.com/problems/maximum-performance-of-a-team/
 */
public class MaximumPerformanceOfATeam {
	private static final int MOD = 1_000_000_007;

	// time O(n*log(n)), space O(k)
	public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
		int[][] engineers = new int[n][2];
		for(int i = 0; i < n; i++){
			engineers[i] = new int[]{speed[i], efficiency[i]};
		}
		Arrays.sort(engineers, (a, b) -> a[1] == b[1] ? b[0] - a[0] : b[1] - a[1]);
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
		long res = Long.MIN_VALUE, toalSpeed = 0;
		for(int[] e : engineers){
			if(pq.size() == k){
				toalSpeed -= pq.poll();
			}
			pq.offer(e[0]);
			toalSpeed += e[0];
			res = Math.max(res, toalSpeed * e[1]);
		}
		return (int)(res % MOD);
	}
}
