package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 2/20/17.
 * #354 https://leetcode.com/problems/russian-doll-envelopes/
 */
public class RussianDollEnvelopes {
	//DP
	public int maxEnvelopes(int[][] envelopes) {
		if (envelopes == null || envelopes.length == 0) {
			return 0;
		}
		int max = 0;
		Arrays.sort(envelopes, new ArrayComparator());
		int len = envelopes.length;
		int[] aux = new int[len];
		Arrays.fill(aux, 1);
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < i; j++) {
				if (envelopes[i][0] > envelopes[j][0]
						&& envelopes[i][1] > envelopes[j][1]) {
					aux[i] = Math.max(aux[i], 1 + aux[j]);
				}
			}
			max = Math.max(aux[i], max);
		}
		return max;
	}
}

class ArrayComparator implements Comparator<int[]> {
	@Override
	public int compare(int[] arr1, int[] arr2) {
		return arr1[0] - arr2[0];
	}
}
