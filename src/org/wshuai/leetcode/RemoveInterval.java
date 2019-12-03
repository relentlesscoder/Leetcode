package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 12/2/19.
 * #1272 https://leetcode.com/problems/remove-interval/
 */
public class RemoveInterval {
	public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
		List<List<Integer>> res = new ArrayList<>();
		for(int i = 0; i < intervals.length; i++){
			int[] cur = intervals[i];
			//cur is at left or right to toBeRemoved
			if(cur[1] <= toBeRemoved[0] || cur[0] >= toBeRemoved[1]){
				res.add(Arrays.asList(cur[0], cur[1]));
				continue;
			}
			//cur is in toBeRemoved
			if(toBeRemoved[0] <= cur[0] && toBeRemoved[1] >= cur[1]){
				continue;
			}
			//cur is cut by toBeRemoved on left
			if(toBeRemoved[0] > cur[0]){
				res.add(Arrays.asList(cur[0], toBeRemoved[0]));
			}
			//cur is cut by toBeRemoved on right
			if(toBeRemoved[1] < cur[1]){
				res.add(Arrays.asList(toBeRemoved[1], cur[1]));
			}
		}
		return res;
	}
}
