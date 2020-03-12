package org.wshuai.leetcode;

/**
 * Created by Wei on 10/09/2019.
 * #0565 https://leetcode.com/problems/array-nesting/
 */
public class ArrayNesting {
	// time O(n), space O(n)
	public int arrayNesting(int[] nums) {
		int res = 0, n = nums.length;
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				continue;
			}
			res = Math.max(res, dfs(i, nums, visited));
		}
		return res;
	}

	private int dfs(int cur, int[] nums, boolean[] visited) {
		if (visited[cur]) {
			return 0;
		}
		visited[cur] = true;
		return 1 + dfs(nums[cur], nums, visited);
	}
}
