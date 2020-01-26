package org.wshuai.leetcode;

/**
 * Created by Wei on 01/24/2020.
 * #0223 https://leetcode.com/problems/rectangle-area/
 */
public class RectangleArea {
	// time O(1)
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int area1 = (C - A) * (D - B),
			area2 = (G - E) * (H - F),
			total = area1 + area2;
		if(C <= E || G <= A || H <= B || D <= F){
			return total;
		}
		int height = (D >= H ? H : D) - Math.max(B, F);
		int width = (G >= C ? C : G) - Math.max(E, A);
		return total - height * width;
	}
}
