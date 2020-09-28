package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/25/2016.
 * #0378 https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class KthSmallestElementInASortedMatrix {

	// time O(log(d)*n*log(n)), d = max - min
	public int kthSmallest(int[][] matrix, int k) {
		int n = matrix.length, left = matrix[0][0], right = matrix[n - 1][n - 1];
		while(left < right){
			int mid = left + (right - left) / 2, count = 0;
			for(int i = 0; i < n; i++){
				count += countLessOrEqual(matrix[i], mid);
			}
			if(count < k){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}

	private int countLessOrEqual(int[] nums, int target){
		int left = 0, right = nums.length;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(nums[mid] <= target){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}

	// time O(k*log(n)), space O(n)
	public int kthSmallestPriorityQueue(int[][] matrix, int k) {
		int n = matrix.length;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		for(int i = 0; i < n; i++){
			pq.offer(new int[]{matrix[i][0], i, 0});
		}
		int[] cur = new int[0];
		for(int i = 0; i < k; i++){
			cur = pq.poll();
			int row = cur[1], col = cur[2];
			if(col != n - 1){
				pq.offer(new int[]{matrix[row][col + 1], row, col + 1});
			}
		}
		return cur[0];
	}
}
