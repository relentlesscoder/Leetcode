package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/21/2019.
 * #835 https://leetcode.com/problems/image-overlap/
 */
public class ImageOverlap {
	public int largestOverlap(int[][] A, int[][] B) {
		int rows = A.length, cols = A[0].length;
		List<int[]> la = new ArrayList<>(), lb = new ArrayList<>();
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				if(A[r][c] == 1){
					la.add(new int[]{r, c});
				}
				if(B[r][c] == 1){
					lb.add(new int[]{r, c});
				}
			}
		}
		Map<String, Integer> map = new HashMap<>();
		for(int[] pa: la){
			for(int[] pb: lb){
				String s = (pa[0] - pb[0]) + " " + (pa[1] - pb[1]);
				map.put(s, map.getOrDefault(s, 0) + 1);
			}
		}
		int max = 0;
		for(int count : map.values()){
			max = Math.max(max, count);
		}
		return max;
	}
}
