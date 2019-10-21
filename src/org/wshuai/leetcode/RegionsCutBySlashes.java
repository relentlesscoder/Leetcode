package org.wshuai.leetcode;

/**
 * Created by Wei on 10/18/2019.
 * #959 https://leetcode.com/problems/regions-cut-by-slashes/
 */
public class RegionsCutBySlashes {
	private int count;
	private int n;
	private int[] f;

	// union find, see https://leetcode.com/problems/regions-cut-by-slashes/discuss/205680/JavaC%2B%2BPython-Split-4-parts-and-Union-Find
	public int regionsBySlashes(String[] grid) {
		n = grid.length;
		f = new int[n * n * 4];
		count = n * n * 4;
		for(int i = 0; i < n * n * 4; i++){
			f[i] = i;
		}
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(i > 0){
					// connect up and down
					union(g(i - 1, j, 2), g(i, j, 0));
				}
				if(j > 0){
					//connect left and right
					union(g(i, j - 1, 1), g(i, j, 3));
				}
				if(grid[i].charAt(j) != '/'){
					union(g(i, j, 0), g(i, j, 1));
					union(g(i, j, 2), g(i, j, 3));
				}
				if(grid[i].charAt(j) != '\\'){
					union(g(i, j, 0), g(i, j, 3));
					union(g(i, j, 2), g(i, j, 1));
				}
			}
		}
		return count;
	}

	private int find(int x){
		if(x != f[x]){
			f[x] = find(f[x]);
		}
		return f[x];
	}

	private void union(int x, int y){
		x = find(x);
		y = find(y);
		if(x != y){
			f[x] = y;
			count--;
		}
	}

	private int g(int i, int j, int k){
		return (i * n + j) * 4 + k;
	}
}
