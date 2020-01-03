package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/18/2019.
 * #711 https://leetcode.com/problems/number-of-distinct-islands-ii/
 */
public class NumberOfDistinctIslandsII {
	private static int[][] dirs = new int[][]{
			{1, -1, 0, 0},
			{0, 0, 1, -1}
	};

	// https://leetcode.com/problems/number-of-distinct-islands-ii/discuss/108794/Consise-C%2B%2B-solution-using-DFS-%2Bsorting-to-find-canonical-representation-for-each-island
	public int numDistinctIslands2(int[][] grid) {
		Set<String> islands = new HashSet<>();
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(grid[i][j] == 1){
					List<int[]> cells = new LinkedList<>();
					dfs(i, j, grid, cells);
					islands.add(norm(cells));
				}
			}
		}
		return islands.size();
	}

	//https://en.wikipedia.org/wiki/Dihedral_group
	private String norm(List<int[]> cells){
		TreeSet<String> normShapes = new TreeSet<>();
		for(int i = -1; i <= 1; i += 2){
			for(int j = -1; j <= 1; j += 2){
				List<int[]> s1 = new ArrayList<>();
				List<int[]> s2 = new ArrayList<>();
				for(int[] cell : cells){
					int x = cell[0];
					int y = cell[1];
					s1.add(new int[]{i * x, j * y});
					s2.add(new int[]{i * y, j * x});
				}
				for(List<int[]> s : Arrays.asList(s1, s2)){
					s.sort(new Comparator<int[]>(){
						@Override
						public int compare(int[] o1, int[] o2){
							return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
						}
					});
					int x = s.get(0)[0];
					int y = s.get(0)[1];
					StringBuilder sb = new StringBuilder();
					for(int[] p : s){
						sb.append(p[0] - x).append(":").append(p[1] - y).append(":");
					}
					normShapes.add(sb.toString());
				}
			}
		}
		return normShapes.first();
	}

	private void dfs(int i, int j, int[][] grid, List<int[]> cells){
		if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1){
			return;
		}
		cells.add(new int[]{i, j});
		grid[i][j] = -1;
		for(int k = 0; k < 4; k++){
			dfs(i + dirs[0][k], j + dirs[1][k], grid, cells);
		}
	}
}
