package org.wshuai.leetcode;

/**
 * Created by Wei on 12/24/2019.
 * #749 https://leetcode.com/problems/contain-virus/
 */
public class ContainVirus {
	private int R;  // Row numbers
	private int C; // col numbers
	private static final int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}}; // 4 directions
	public int containVirus(int[][] grid)
	{
		R = grid.length;
		C = grid[0].length;
		int ans = 0;
		while (true)
		{
			int walls = process(grid);
			ans += walls;
			if(walls == 0)break;   // no new walls to build
		}
		return ans;
	}

	private int process(int[][] grid)
	{
		// maxArea is max area to be affected by a single virus region; ans is corresponding walls
		int maxArea = 0, ans = 0, color = -1, row = -1, col = -1;
		// visited virus as 1, visited 0 using different color to indicate being affected by different virus

		int[][] visited = new int[R][C];

		// find the max zero area.
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(grid[i][j] == 1 && visited[i][j] == 0)
				{
					int[] walls = {0};
					int area = dfs(grid, visited, i, j, color, walls);
					if(area > maxArea)
					{
						maxArea = area;
						ans = walls[0];
						row = i;
						col = j;
					}
					color--; // different islands using different color
				}
			}
		}
		// remove virus region.
		removeIsland(grid, row, col);
		// spread by one step
		visited = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(grid[i][j] == 1 && visited[i][j] == 0)
				{
					spread(grid, visited, i,j);
				}
			}
		}
		return ans;
	}

	// get the area of zero around the island 1
	// different one island using different color.
	private int dfs(int[][] grid, int[][] visited, int r, int c, int color, int[] walls)
	{
		if(r < 0 || r > R -1 || c < 0 || c > C -1 )return 0;
		if(grid[r][c] == 0)
		{
			// for already visited zero cell, we need to build one more wall
			// but we don't need to count it into the area
			walls[0]++;
			if(visited[r][c] == color)return 0; // already visited this zero point.
			visited[r][c] = color;
			return 1;  // one zero point.
		}

		// already visited point and removed islands
		if(visited[r][c] == 1 || grid[r][c] != 1)return 0;

		// set this point as visited.
		visited[r][c] = 1;
		int ans = 0;
		for(int[] dir: dirs)
		{
			int x = r + dir[0];
			int y = c + dir[1];
			ans += dfs(grid, visited, x, y, color, walls);  // sum all zero point.
		}
		return ans;
	}

	private void removeIsland(int[][] grid, int r, int c)
	{
		if(r < 0 || r > R -1 || c < 0 || c > C -1 || grid[r][c] != 1)return;
		grid[r][c] = -1; // remove it
		for(int[] dir: dirs)
		{
			int x = r + dir[0];
			int y = c + dir[1];
			removeIsland(grid, x,y);
		}
	}

	private void spread(int[][] grid, int[][] visited, int r, int c)
	{
		if(r < 0 || r > R -1 || c < 0 || c > C -1 || visited[r][c] == 1)return;
		if(grid[r][c] == -1)return;  // removed virus region, ignore it

		visited[r][c] = 1;   // set current point as visited.
		if(grid[r][c] == 0)
		{
			grid[r][c] = 1;
			return;
		}

		// adjacent position
		for(int[] dir: dirs)
		{
			int x = r + dir[0];
			int y = c + dir[1];
			spread(grid, visited, x,y);
		}

	}
}
