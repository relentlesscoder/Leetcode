package org.wshuai.leetcode;

/**
 * Created by Wei on 10/19/2019.
 * #0799 https://leetcode.com/problems/champagne-tower/
 */
public class ChampagneTower {
	// time O(n^2)
	public double champagneTower(int poured, int query_row, int query_glass) {
		double[] cur = new double[100], next = new double[100];
		cur[0] = poured;
		for(int i = 0, j = 1; i < query_row; i++, j++){
			for(int k = 0; k < j; k++){
				double fall = cur[k] > 1 ? cur[k] - 1 : 0;
				next[k] += 0.5 * fall;
				next[k + 1] += 0.5 * fall;
			}
			cur = next;
			next = new double[100];
		}
		return cur[query_glass] > 1 ? 1 : cur[query_glass];
	}
}
