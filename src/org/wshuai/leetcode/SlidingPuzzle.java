package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 11/22/2019.
 * #0773 https://leetcode.com/problems/sliding-puzzle/
 */
public class SlidingPuzzle {

	// valid moves from each index in [0, 5]
	private static final int[][] dirs = new int[][]{
		{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}
	};

	public int slidingPuzzle(int[][] board) {
		int moves = 0;
		String original = "" + board[0][0] + board[0][1] + board[0][2]
			+ board[1][0] + board[1][1] + board[1][2], target = "123450";
		if(original.equals(target)){
			return 0;
		}
		LinkedList<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		queue.offerLast(original);
		visited.add(original);
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				char[] cur = queue.pollFirst().toCharArray();
				int i = 0;
				for(; i < 6; i++){
					if(cur[i] == '0'){
						break;
					}
				}
				for(int j : dirs[i]){
					swap(cur, i, j);
					String next = String.valueOf(cur);
					if(target.equals(next)){
						return moves + 1;
					}
					if(visited.add(next)){
						queue.offerLast(next);
					}
					swap(cur, i, j);
				}
			}
			moves++;
		}
		return -1;
	}

	public int slidingPuzzleBiDirectionalBFS(int[][] board) {
		String original = "" + board[0][0] + board[0][1] + board[0][2] + board[1][0] + board[1][1] + board[1][2], target = "123450";
		if(original.equals(target)){
			return 0;
		}
		int moves = 0;
		Set<String> from = new HashSet<>(), to = new HashSet<>(), visited = new HashSet<>(), next;
		from.add(original);
		to.add(target);
		visited.add(original);
		while(from.size() > 0 && to.size() > 0){
			if(from.size() > to.size()){
				Set<String> temp = from;
				from = to;
				to = temp;
			}
			next = new HashSet<>();
			for(String val : from){
				char[] cur = val.toCharArray();
				int i = 0;
				for(; i < 6; i++){
					if(cur[i] == '0'){
						break;
					}
				}
				for(int j : dirs[i]){
					swap(cur, i, j);
					String str = String.valueOf(cur);
					if(to.contains(str)){
						return moves + 1;
					}
					if(visited.add(str)){
						next.add(str);
					}
					swap(cur, i, j);
				}
			}
			from = next;
			moves++;
		}
		return -1;
	}

	private void swap(char[] arr, int from, int to){
		char temp = arr[from];
		arr[from] = arr[to];
		arr[to] = temp;
	}
}
