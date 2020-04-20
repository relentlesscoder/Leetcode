package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/31/2019.
 * #0757 https://leetcode.com/problems/set-intersection-size-at-least-two/
 */
public class SetIntersectionSizeAtLeastTwo {
	// time O(n)
	// https://leetcode.com/problems/set-intersection-size-at-least-two/discuss/113085/Ever-wonder-why-the-greedy-algorithm-works-Here-is-the-explanation!
	// https://www.cnblogs.com/grandyang/p/8503476.html
	public int intersectionSizeTwo(int[][] intervals) {
		int res = 0, largest = -1, secondLargest = -1;
		Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);
		for (int[] in : intervals) {
			int start = in[0], end = in[1];
			if (start <= secondLargest) {
				continue;
			} else if (start <= largest) {
				res++;
				secondLargest = largest;
				largest = end;
			} else {
				res += 2;
				secondLargest = end - 1;
				largest = end;
			}
		}
		return res;
	}
}
