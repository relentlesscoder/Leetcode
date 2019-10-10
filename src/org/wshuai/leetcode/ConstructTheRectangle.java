package org.wshuai.leetcode;

/**
 * Created by Wei on 3/10/17.
 * #492 https://leetcode.com/problems/construct-the-rectangle/
 */
public class ConstructTheRectangle {
	//5ms
	public int[] constructRectangle(int area) {
		int[] res = new int[2];
		int w = (int) Math.sqrt(area);
		while (area % w != 0) {
			w--;
		}
		res[0] = area / w;
		res[1] = w;
		return res;
	}

	//O(n), 568ms
	public int[] constructRectangleSlow(int area) {
		int[] res = new int[2];
		int minDiff = Integer.MAX_VALUE;
		for (int l = area; l > 0; l--) {
			if (area % l == 0) {
				int w = area / l;
				if (l >= w && l - w < minDiff) {
					minDiff = l - w;
					res[0] = l;
					res[1] = w;
				}
				if (l < w) {
					break;
				}
			}
		}
		return res;
	}
}
