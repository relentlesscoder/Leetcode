package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 9/24/2016.
 */
public class HIndex {
	public int hIndex(int[] citations) {
		if (citations == null) {
			throw new IllegalArgumentException("Invalid input");
		}
		int len = citations.length;
		int hidx = 0;
		Arrays.sort(citations);
		for (int i = 0; i < len; i++) {
			int val = citations[i];
			int cidx = Math.min(val, len - i);
			hidx = Math.max(hidx, cidx);
		}
		return hidx;
	}
}
