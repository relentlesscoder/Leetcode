package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 2/20/17.
 * #475 https://leetcode.com/problems/heaters/
 */
public class Heaters {
	public int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(heaters);
		int len = houses.length;
		int res = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			int house = houses[i];
			int idx = Arrays.binarySearch(heaters, house);
			//cannot find house in heaters
			if (idx < 0) {
				//get insert position
				idx = -(idx + 1);
				//corner case 1 - house located at the left of all heaters
				int left = idx - 1 >= 0 ? house - heaters[idx - 1] : heaters[0] - house;
				//corner case 2 - house located at the right of all heaters
				int right = idx < heaters.length ? heaters[idx] - house : house - heaters[heaters.length - 1];
				res = Math.max(res, Math.min(left, right));
			} else {
				//find house in heaters, which means the required min radius is 0
				res = Math.max(res, 0);
			}
		}
		return res;
	}
}
