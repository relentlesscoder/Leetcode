package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/11/2019.
 * #947 https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 */
public class MostStonesRemovedWithSameRowOrColumn {
	int count = 0;

	public int removeStones(int[][] stones) {
		Map<Integer, Integer> parent = new HashMap<>();
		count = stones.length;

		for(int[] stone: stones){
			int s = stone[0] * 10_000 + stone[1];
			parent.put(s, s);
		}
		for(int[] s1: stones){
			int ss1 = s1[0] * 10_000 + s1[1];
			for(int[] s2: stones){
				if(s1[0] == s2[0] || s1[1] == s2[1]){
					int ss2 = s2[0] * 10_000 + s2[1];
					if(ss1 == ss2){
						continue;
					}
					union(parent, ss1, ss2);
				}
			}
		}
		return stones.length - count;
	}

	private void union(Map<Integer, Integer> parent, int s1, int s2){
		int r1 = find(parent, s1);
		int r2 = find(parent, s2);
		if(r1 == r2){
			return;
		}
		parent.put(r1, r2);
		count--;
	}

	private int find(Map<Integer, Integer> parent, int s){
		while(parent.get(s) != s){
			s = parent.get(s);
		}
		return parent.get(s);
	}
}
