package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 10/24/2020.
 * #1610 https://leetcode.com/problems/maximum-number-of-visible-points/
 */
public class MaximumNumberOfVisiblePoints {

	// time O(n*log(n)), space O(n)
	// https://leetcode.com/problems/maximum-number-of-visible-points/discuss/877845/JAVA-Sliding-Window
	public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
		List<Double> angles = new ArrayList<>();
		int res = 0, dup = 0;
		for(List<Integer> p : points){
			int dx = p.get(0) - location.get(0);
			int dy = p.get(1) - location.get(1);
			if(dx == 0 && dy == 0){
				dup++;
				continue;
			}
			angles.add(Math.atan2(dy, dx) * (180 / Math.PI));
		}
		Collections.sort(angles);
		List<Double> temp = new ArrayList<>(angles);
		for(double d : angles){
			temp.add(d + 360);
		}
		for (int i = 0, j = 0; i < temp.size(); i++) {
			while (temp.get(i) - temp.get(j) > angle) {
				j++;
			}
			res = Math.max(res, i - j + 1);
		}
		return res + dup;
	}
}
