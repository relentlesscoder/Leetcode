package org.wshuai.leetcode;

/**
 * Created by Wei on 01/17/2021.
 * #1725 https://leetcode.com/problems/number-of-rectangles-that-can-form-the-largest-square/
 */
public class NumberOfRectanglesThatCanFormTheLargestSquare {

	// time O(n)
	public int countGoodRectangles(int[][] rectangles) {
		int res = 0, max = 0;
		for(int[] rect : rectangles){
			int cur = Math.min(rect[0], rect[1]);
			if(cur == max){
				res++;
			}else if(cur > max){
				res = 1;
				max = cur;
			}
		}
		return res;
	}
}
