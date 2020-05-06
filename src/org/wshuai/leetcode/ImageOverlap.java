package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/21/2019.
 * #0835 https://leetcode.com/problems/image-overlap/
 */
public class ImageOverlap {

	// time O(n^2), space O(2*n)
	public int largestOverlap(int[][] A, int[][] B) {
		int res = 0, n = A.length;
		Map<String, Integer> map = new HashMap<>();
		List<int[]> la = new ArrayList<>(), lb = new ArrayList<>();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(A[i][j] == 1){
					la.add(new int[]{i, j});
				}
				if(B[i][j] == 1){
					lb.add(new int[]{i, j});
				}
			}
		}
		for(int[] a : la){
			for(int[] b : lb){
				String key = (a[0] - b[0]) + "," + (a[1] - b[1]);
				int d = map.getOrDefault(key, 0) + 1;
				map.put(key, d);
				res = Math.max(res, d);
			}
		}
		return res;
	}
}
