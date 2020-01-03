package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 11/23/19.
 * #499 https://leetcode.com/problems/the-maze-iii/
 */
public class TheMazeIII {
	public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
		int[][] dirs = new int[][]{
			{1, 0, 0, -1},
			{0, -1, 1, 0}
		};
		String[] map = new String[]{
			"d", "l", "r", "u"
		};
		int R = maze.length;
		int C = maze[0].length;
		LinkedList<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		// 0 -> down, 1 -> left, 2 -> right, 3 -> up
		for(int k = 0; k < 4; k++){
			String res = map[k];
			int x = ball[0] + dirs[0][k];
			int y = ball[1] + dirs[1][k];
			if(x >= 0 && x < R && y >= 0 && y < C && maze[x][y] != 1){
				if(x == hole[0] && y == hole[1]){
					return res;
				}
				String val = x + "," + y + "," + k;
				queue.offerLast(val);
				visited.add(val);
			}
		}
		while(!queue.isEmpty()){
			String prev = queue.poll();
			String[] vals = prev.split(",");
			int i = Integer.parseInt(vals[0]);
			int j = Integer.parseInt(vals[1]);
			char c = vals[2].charAt(vals[2].length() - 1);
			int d = Integer.parseInt(String.valueOf(c));
			boolean redirect = false;
			if(d == 0 && (i == R - 1 || maze[i + 1][j] == 1)){
				redirect = true;
			}else if(d == 3 && (i == 0 || maze[i - 1][j] == 1)){
				redirect = true;
			}else if(d == 1 && (j == 0 || maze[i][j - 1] == 1)){
				redirect = true;
			}else if(d == 2 && (j == C - 1 || maze[i][j + 1] == 1)){
				redirect = true;
			}
			if(redirect){
				for(int k = 0; k < 4; k++){
					String res = getDirection(vals[2]) + map[k];
					int x = i + dirs[0][k];
					int y = j + dirs[1][k];
					if(x >= 0 && x < R && y >= 0 && y < C && maze[x][y] != 1){
						if(x == hole[0] && y == hole[1]){
							return res;
						}
						String val = x + "," + y + ",";
						if(visited.add(val + k)){
							queue.offerLast(val + vals[2] + k);
						}
					}
				}
			}else{
				int x = i + dirs[0][d];
				int y = j + dirs[1][d];
				if(x >= 0 && x < R && y >= 0 && y < C && maze[x][y] != 1){
					if(x == hole[0] && y == hole[1]){
						return getDirection(vals[2]);
					}
					String val = x + "," + y + ",";
					if(visited.add(val + d)){
						queue.offerLast(val + vals[2]);
					}
				}
			}
		}
		return "impossible";
	}

	private String getDirection(String s){
		StringBuilder sb = new StringBuilder();
		for(char c : s.toCharArray()){
			if(c == '0'){
				sb.append("d");
			}else if(c == '1'){
				sb.append("l");
			}else if(c == '2'){
				sb.append("r");
			}else{
				sb.append("u");
			}
		}
		return sb.toString();
	}
}