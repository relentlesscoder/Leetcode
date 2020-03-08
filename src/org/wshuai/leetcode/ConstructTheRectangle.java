package org.wshuai.leetcode;

/**
 * Created by Wei on 03/10/17.
 * #0492 https://leetcode.com/problems/construct-the-rectangle/
 */
public class ConstructTheRectangle {
	// time O(log(n))
	public int[] constructRectangle(int area) {
		int width = (int)Math.sqrt(area);
		while(area % width != 0){
			width--;
		}
		return new int[]{area / width, width};
	}
}
