package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 10/28/19.
 * #886 https://leetcode.com/problems/possible-bipartition/
 */
public class PossibleBipartition {
	public boolean possibleBipartition(int N, int[][] dislikes) {
		int[] groups = new int[N];
		LinkedList<Integer>[] graph = new LinkedList[N];
		for(int i = 0; i < N; i++){
			graph[i] = new LinkedList<>();
		}
		for(int[] d: dislikes){
			graph[d[0] - 1].offerLast(d[1] - 1);
			graph[d[1] - 1].offerLast(d[0] - 1);
		}
		for(int i = 0; i < N; i++){
			if(groups[i] == 0 && !isValidGroup(graph, groups, 1, i)){
				return false;
			}
		}

		return true;
	}

	private boolean isValidGroup(LinkedList<Integer>[] graph, int[] groups, int color, int curr){
		if(groups[curr] != 0){
			return groups[curr] == color;
		}
		groups[curr] = color;
		for(int j : graph[curr]){
			if(!isValidGroup(graph, groups, -color, j)){
				return false;
			}
		}
		return true;
	}
}
