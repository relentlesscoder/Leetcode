package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/16/2019.
 * #1222 https://leetcode.com/problems/queens-that-can-attack-the-king/
 */
public class QueensThatCanAttackTheKing {
	public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
		List<List<Integer>> res = new ArrayList<>();
		int[][] move = new int[][]{
				{-1, -1, -1, 0, 1, 1, 1, 0},
				{-1, 0, 1, 1, 1, 0, -1, -1}
		};
		Map<Integer, int[]> map = new HashMap<>();
		for(int[] q: queens){
			map.put(q[0] * 8 + q[1], q);
		}
		for(int i = 0; i < 8; i++){
			int x = king[0];
			int y = king[1];
			while(isValid(x, y) && !map.containsKey(x * 8 + y)){
				x += move[0][i];
				y += move[1][i];
			}
			if(isValid(x, y)){
				List<Integer> lst = new ArrayList<>();
				lst.add(x);
				lst.add(y);
				res.add(lst);
			}
		}
		return res;
	}

	private boolean isValid(int x, int y){
		return x >= 0 && y >= 0 && x < 8 && y < 8;
	}
}
