package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 10/03/2023.
 * #1860 https://leetcode.com/problems/incremental-memory-leak/
 */
public class IncrementalMemoryLeak {

	// time O(sqrt(m1 + m2)), space O(1)
	public int[] memLeak(int memory1, int memory2) {
		int[] res = new int[3];
		int bits = 1;
		while (memory1 >= bits || memory2 >= bits) {
			if (memory1 >= memory2) {
				memory1 -= bits;
			} else {
				memory2 -= bits;
			}
			bits++;
		}
		res[0] = bits;
		res[1] = memory1;
		res[2] = memory2;
		return res;
	}
}
