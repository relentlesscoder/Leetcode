package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 08/06/2019.
 * #0986 https://leetcode.com/problems/interval-list-intersections/
 */
public class IntervalListIntersections {

	// time O(n)
	public int[][] intervalIntersection(int[][] A, int[][] B) {
		List<int[]> res = new ArrayList<>();
		for(int i = 0, j = 0; i < A.length && j < B.length; ){
			int low = Math.max(A[i][0], B[j][0]);
			int high = Math.min(A[i][1], B[j][1]);
			if(low <= high){
				res.add(new int[]{low, high});
			}
			if(A[i][1] < B[j][1]){
				i++;
			}else{
				j++;
			}
		}
		return res.toArray(new int[res.size()][2]);
	}

}
