package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 11/22/19.
 * #773 https://leetcode.com/problems/sliding-puzzle/
 */
public class SlidingPuzzle {
	public int slidingPuzzle(int[][] board) {
		String target = "123450";
		String start = "" + board[0][0] + board[0][1] + board[0][2]
			+ board[1][0] + board[1][1] + board[1][2];
		if(start.equals(target)){
			return 0;
		}
		int[][] dirs = new int[][]{
			{1, 3}, {0, 2, 4}, {1, 5},
			{0, 4}, {3, 1, 5}, {2, 4}
		};
		Set<String> visited = new HashSet<String>();
		LinkedList<String> queue = new LinkedList<>();
		queue.offerLast(start);
		visited.add(start);
		int step = 0;
		while(!queue.isEmpty()){
			int size = queue.size();
			step++;
			while(size-- > 0){
				String prev = queue.pollFirst();
				int i = 0;
				for(; i < 6; i++){
					if(prev.charAt(i) == '0'){
						break;
					}
				}
				int[] swap = dirs[i];
				for(int s : swap){
					char[] p = prev.toCharArray();
					p[i] = p[s];
					p[s] = '0';
					String next = new String(p);
					if(next.equals(target)){
						return step;
					}
					if(visited.add(next)){
						queue.offerLast(next);
					}
				}
			}
		}
		return -1;
	}
}
