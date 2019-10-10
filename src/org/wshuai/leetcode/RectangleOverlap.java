package org.wshuai.leetcode;

/**
 * Created by Wei on 9/17/19.
 * #836 https://leetcode.com/problems/rectangle-overlap/
 */
public class RectangleOverlap {
	public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
		return !(rec1[2] <= rec2[0] || //left
				rec1[1] >= rec2[3] ||    //up
				rec1[0] >= rec2[2] ||    //right
				rec1[3] <= rec2[1]);     //down
	}
}
