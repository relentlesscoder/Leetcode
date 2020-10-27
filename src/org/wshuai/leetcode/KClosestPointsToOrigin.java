package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 08/04/2019.
 * #0973 https://leetcode.com/problems/k-closest-points-to-origin/
 */
public class KClosestPointsToOrigin {

	// time O(n)
	public int[][] kClosest(int[][] points, int K) {
		int n = points.length, left = 0, right = n - 1;
		while(left <= right){
			int mid = findPivot(points, left, right);
			if(mid == K){
				break;
			}
			if(mid < K){
				left = mid + 1;
			}else{
				right = mid - 1;
			}
		}
		return Arrays.copyOfRange(points, 0, K);
	}

	private int findPivot(int[][] points, int left, int right){
		int rand = left + new Random().nextInt(right - left + 1);
		swap(points, rand, right);
		int dist = distance(points[right]);
		int pivot = left;
		for(int i = left; i < right; i++){
			if(distance(points[i]) < dist){
				int[] temp = points[pivot];
				points[pivot++] = points[i];
				points[i] = temp;
			}
		}
		swap(points, pivot, right);
		return pivot;
	}

	private void swap(int[][] points, int left, int right){
		int[] swap = points[left];
		points[left] = points[right];
		points[right] = swap;
	}

	private int distance(int[] p){
		return p[0]*p[0] + p[1]*p[1];
	}

	// time n*log(k), space O(k)
	public int[][] kClosestPriorityQueue(int[][] points, int K) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0]*b[0] + b[1]*b[1] - a[0]*a[0] - a[1]*a[1]);
		for(int[] p : points){
			pq.offer(p);
			if(pq.size() > K){
				pq.poll();
			}
		}
		int[][] res = new int[K][2];
		int i = 0;
		while(!pq.isEmpty()){
			res[i++] = pq.poll();
		}
		return res;
	}

	// time O(n*log(n))
	public int[][] kClosestSorting(int[][] points, int K) {
		Arrays.sort(points, (a, b) -> (a[0]*a[0] + a[1]*a[1] - b[0]*b[0] - b[1]*b[1]));
		return Arrays.copyOfRange(points, 0, K);
	}
}
