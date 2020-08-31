package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 08/30/2020.
 * #1560 https://leetcode.com/problems/most-visited-sector-in-a-circular-track/
 */
public class MostVisitedSectorInACircularTrack {

	// time O(n), space O(n)
	public List<Integer> mostVisited(int n, int[] rounds) {
		int r = rounds.length, start = rounds[0], end = rounds[r - 1];
		List<Integer> res = new ArrayList<>();
		if(end >= start){
			for(int i = start; i <= end; i++){
				res.add(i);
			}
		}else{
			for(int i = 1; i <= n; i++){
				if(i == end + 1){
					i = start;
				}
				res.add(i);
			}
		}
		return res;
	}
}
