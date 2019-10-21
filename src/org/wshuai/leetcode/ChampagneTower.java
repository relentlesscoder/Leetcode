package org.wshuai.leetcode;

/**
 * Created by Wei on 10/19/2019.
 * #799 https://leetcode.com/problems/champagne-tower/
 */
public class ChampagneTower {
	public double champagneTower(int poured, int query_row, int query_glass) {
		double[] cups = new double[100];
		int row = 0;
		cups[0] = poured;
		while(row < query_row){
			int cnt = row + 1;
			double[] next = new double[100];
			for(int i = 0; i < cnt; i++){
				next[i] += 0.5 * (cups[i] > 1 ? cups[i] - 1 : 0);
				next[i + 1] += 0.5 * (cups[i] > 1 ? cups[i] - 1 : 0);
			}
			cups = next;
			row++;
		}
		return cups[query_glass] > 1 ? 1 : cups[query_glass];
	}
}
