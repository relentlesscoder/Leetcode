package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 06/14/2020.
 * #1476 https://leetcode.com/problems/subrectangle-queries/
 */
public class SubrectangleQueries {

	private LinkedList<int[]> queries;
	private int[][] rect;

	public SubrectangleQueries(int[][] rectangle) {
		queries = new LinkedList<>();
		rect = rectangle;
	}

	public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
		queries.offerFirst(new int[]{row1, col1, row2, col2, newValue});
	}

	public int getValue(int row, int col) {
		for(int[] q : queries){
			if(row >= q[0] && row <= q[2] && col >= q[1] && col <= q[3]){
				return q[4];
			}
		}
		return rect[row][col];
	}
}
