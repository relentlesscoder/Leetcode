package org.wshuai.leetcode;

/**
 * Created by Wei on 10/01/2019.
 * #0573 https://leetcode.com/problems/squirrel-simulation/
 */
public class SquirrelSimulation {
	// time O(n), space O(n)
	public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
		int res = Integer.MAX_VALUE, n = nuts.length, sum = 0;
		int[] dist = new int[n];
		for (int i = 0; i < n; i++) {
			dist[i] = distance(tree, nuts[i]);
			sum += (dist[i] << 1);
		}
		for (int i = 0; i < n; i++) {
			res = Math.min(res, sum + distance(nuts[i], squirrel) - dist[i]);
		}
		return res;
	}

	private int distance(int[] p1, int[] p2) {
		return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
	}
}
