package org.wshuai.leetcode;

/**
 * Created by Wei on 08/03/2020.
 * #1536 https://leetcode.com/problems/minimum-swaps-to-arrange-a-binary-grid/
 */
public class MinimumSwapsToArrangeABinaryGrid {

	// time O(n^2), space O(n)
	public int minSwaps(int[][] grid) {
		int res = 0, n = grid.length, needed = n - 1;
		int[] rows = new int[n];
		for(int i = 0; i < n; i++){
			int count = 0;
			for(int j = n - 1; j >= 0; j--){
				if(grid[i][j] == 1){
					break;
				}
				count++;
			}
			rows[i] = count;
		}
		boolean[] used = new boolean[n];
		while(needed > 0){
			boolean found = false;
			for(int i = 0; i < n; i++){
				if(used[i]){
					continue;
				}
				if(rows[i] >= needed){
					found = true;
					used[i] = true;
					break;
				}else{
					res++;
				}
			}
			if(!found){
				return -1;
			}
			needed--;
		}
		return res;
	}
}
