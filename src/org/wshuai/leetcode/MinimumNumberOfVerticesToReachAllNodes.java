package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 08/31/2020.
 * #1557 https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/
 */
public class MinimumNumberOfVerticesToReachAllNodes {

	// time O(n), space O(n)
	public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
		int[] indegree = new int[n];
		for(List<Integer> edge : edges){
			indegree[edge.get(1)]++;
		}
		List<Integer> res = new ArrayList<>();
		for(int i = 0; i < n; i++){
			if(indegree[i] != 0){
				continue;
			}
			res.add(i);
		}
		return res;
	}
}
