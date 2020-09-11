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
		int pivot = distance(points[right]);
		int p = left;
		for(int i = left; i < right; i++){
			if(distance(points[i]) < pivot){
				int[] temp = points[p];
				points[p++] = points[i];
				points[i] = temp;
			}
		}
		int[] swap = points[p];
		points[p] = points[right];
		points[right] = swap;
		return p;
	}

	private int distance(int[] p){
		return p[0]*p[0] + p[1]*p[1];
	}

	// time O(n*log(n))
	public int[][] kClosestSorting(int[][] points, int K) {
		Arrays.sort(points, (a, b) -> (a[0]*a[0] + a[1]*a[1] - b[0]*b[0] - b[1]*b[1]));
		return Arrays.copyOfRange(points, 0, K);
	}
}
