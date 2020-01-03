package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 12/3/19.
 * #1273 https://leetcode.com/problems/delete-tree-nodes/
 */
public class DeleteTreeNodes {
	public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
		for(int i = nodes - 1; i >= 0; i--){
			if(parent[i] == -1){
				continue;
			}
			value[parent[i]] += value[i];
		}
		Set<Integer> zeros = new HashSet<>();
		int cnt = 0;
		for(int i = 0; i < nodes; i++){
			if(value[i] == 0 || zeros.contains(parent[i])){
				cnt++;
				zeros.add(i);
			}
		}
		return nodes - cnt;
	}
}
