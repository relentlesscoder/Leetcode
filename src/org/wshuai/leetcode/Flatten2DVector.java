package org.wshuai.leetcode;

/**
 * Created by Wei on 11/06/2016.
 * #0251 https://leetcode.com/problems/flatten-2d-vector/
 */
public class Flatten2DVector{
	private int[][] v;
	private int i, j;

	public Flatten2DVector(int[][] v) {
		this.v = v;
		i = j = 0;
	}

	public int next() {
		while(j == v[i].length){
			i++;
			j = 0;
		}
		return v[i][j++];
	}

	public boolean hasNext() {
		while(i < v.length && j == v[i].length){
			i++;
			j = 0;
		}
		return i < v.length;
	}
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(v);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
