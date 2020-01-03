package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 12/18/2019.
 * #862 https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
 */
public class ShortestSubarrayWithSumAtLeastK {
	public int shortestSubarray(int[] A, int K) {
		int N = A.length;
		int res = N + 1;
		int[] B = new int[N + 1];
		for(int i = 0; i < N; i++){
			B[i + 1] = B[i] + A[i];
		}
		LinkedList<Integer> queue = new LinkedList<>();
		for(int  i = 0; i < N + 1; i++){
			while(queue.size() > 0 && B[i] - B[queue.peekFirst()] >= K){
				res = Math.min(res, i - queue.pollFirst());
			}
			while(queue.size() > 0 && B[i] <= B[queue.peekLast()]){
				queue.pollLast();
			}
			queue.offerLast(i);
		}
		return res <= N ? res : -1;
	}
}
