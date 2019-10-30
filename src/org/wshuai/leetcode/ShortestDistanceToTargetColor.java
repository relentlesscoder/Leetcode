package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/29/2019.
 * #1182 https://leetcode.com/problems/shortest-distance-to-target-color/
 */
public class ShortestDistanceToTargetColor {
	public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
		List<Integer>[] map = new ArrayList[3];
		for(int i = 0; i < 3; i++){
			map[i] = new ArrayList<>();
		}
		for(int i = 0; i < colors.length; i++){
			map[colors[i] - 1].add(i);
		}
		List<Integer> res = new ArrayList<>();
		for(int[] q : queries){
			if(map[q[1] - 1].size() == 0){
				res.add(-1);
				continue;
			}
			res.add(binarySearch(q[0], map[q[1] - 1]));
		}
		return res;
	}

	private int binarySearch(int t, List<Integer> nums){
		int left = 0;
		int right = nums.size() - 1;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(nums.get(mid) < t){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		int res = left;
		if(left - 1 >= 0 && t - nums.get(left - 1) < nums.get(left) - t){
			res = left - 1;
		}
		return Math.abs(nums.get(res) - t);
	}
}
