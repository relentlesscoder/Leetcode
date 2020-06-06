package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 06/01/2020.
 * #1465 https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
 */
public class MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts {

	// time O(n*log(n))
	public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
		int prevHorizontalCut = 0, prevVerticalCut = 0, maxHorizontal = Integer.MIN_VALUE, maxVertical = Integer.MIN_VALUE;
		Arrays.sort(horizontalCuts);
		Arrays.sort(verticalCuts);
		for(int i = 0; i < horizontalCuts.length; i++){
			maxHorizontal = Math.max(maxHorizontal, horizontalCuts[i] - prevHorizontalCut);
			prevHorizontalCut = horizontalCuts[i];
		}
		maxHorizontal = Math.max(maxHorizontal, h - prevHorizontalCut);
		for(int j = 0; j < verticalCuts.length; j++){
			maxVertical = Math.max(maxVertical, verticalCuts[j] - prevVerticalCut);
			prevVerticalCut = verticalCuts[j];
		}
		maxVertical = Math.max(maxVertical, w - prevVerticalCut);
		return (int)(((long)maxHorizontal * (long)maxVertical) % 1_000_000_007);
	}
}
