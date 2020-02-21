package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/12/2016.
 * #0406 https://leetcode.com/problems/queue-reconstruction-by-height/
 */
public class QueueReconstructionByHeight {
	// time O(n)
	// https://leetcode.com/articles/queue-reconstruction-by-height/
	public int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
		List<int[]> res = new ArrayList<>();
		// key observation - insert lower element into the queue
		// will not affect the k value of taller element after it.
		for(int[] p : people){
			res.add(p[1], p);
		}
		return res.toArray(new int[res.size()][2]);
	}
}
