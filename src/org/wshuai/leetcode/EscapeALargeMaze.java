package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 11/24/19.
 * #1036 https://leetcode.com/problems/escape-a-large-maze/
 */
public class EscapeALargeMaze {
	public boolean isEscapePossible(int[][] blocked, int[] source, int[] target){
		if(blocked.length == 0){
			return true;
		}
		Set<String> set = new HashSet<>();
		for(int[] b : blocked){
			set.add(b[0] + "," + b[1]);
		}
		return bfs(set, source, target)
			&& bfs(set, target, source);
	}

	public boolean bfs(Set<String> blocked, int[] source, int[] target) {
		int limit = 1_000_000;
		int[][] dirs = new int[][]{
			{1, -1, 0, 0},
			{0, 0, 1, -1}
		};
		LinkedList<int[]> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		queue.offerLast(source);
		visited.add(source[0] + "," + source[1]);
		while(!queue.isEmpty()){
			int[] cur = queue.pollFirst();
			for(int k = 0; k < 4; k++){
				int x = cur[0] + dirs[0][k];
				int y = cur[1] + dirs[1][k];
				String val = x + "," + y;
				if(x == target[0] && y == target[1]){
					return true;
				}
				if(x >= 0 && x < limit && y >= 0 && y < limit
					&& !blocked.contains(val) && visited.add(val)){
					queue.offerLast(new int[]{x, y});
				}
			}
			if(visited.size() > 20_000){
				return true;
			}
		}
		return false;
	}
}
