package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 12/9/2019.
 * #1284 https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/
 */
public class MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix {
	// BFS
	public int minFlips(int[][] mat) {
		int[][] dirs = new int[][]{
			{1, -1, 0, 0},
			{0, 0, 1, -1}
		};
		int r = mat.length;
		int c = mat[0].length;
		int init = 0;
		for(int i = 0; i < r; i ++){
			for(int j = 0; j < c; j++){
				init <<= 1;
				init |= mat[i][j];
			}
		}
		if(init == 0){
			return 0;
		}
		Set<Integer> visited = new HashSet<>();
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offerLast(init);
		visited.add(init);
		int steps = 0;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int cur = queue.pollFirst();
				for(int i = 0; i < r; i++){
					for(int j = 0; j < c; j++){
						int next = cur;
						// notice that 1 shift 1 bit less than 0
						int pos = r*c - 1 - i*c - j;
						next ^= (1 << pos);
						for(int k = 0; k < 4; k++){
							int x = i + dirs[0][k];
							int y = j + dirs[1][k];
							if(x >= 0 && y >= 0 && x < r && y < c){
								next ^= (1 << (r*c - 1 - x*c - y));
							}
						}
						if(next == 0){
							return steps + 1;
						}
						if(visited.add(next)){
							queue.offerLast(next);
						}
					}
				}
			}
			steps++;
		}
		return -1;
	}
}
