package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/31/2019.
 * #757 https://leetcode.com/problems/set-intersection-size-at-least-two/
 */
public class SetIntersectionSizeAtLeastTwo {
	// https://leetcode.com/problems/set-intersection-size-at-least-two/discuss/113085/Ever-wonder-why-the-greedy-algorithm-works-Here-is-the-explanation!
	public int intersectionSizeTwo(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> a[1] != b[1] ? a[1] - b[1] : b[0] - a[0]);
		int m = 0, largest = -1, second = -1;
		for(int[] interval : intervals){
			int s = interval[0];
			int e = interval[1];
			boolean isLargestIn = (s <= largest);
			boolean isSecondIn = (s <= second);
			if(isLargestIn && isSecondIn){
				continue;
			}
			m += isLargestIn ? 1 : 2;
			second = isLargestIn ? largest : e - 1;
			largest = e;
		}
		return m;
	}
}
