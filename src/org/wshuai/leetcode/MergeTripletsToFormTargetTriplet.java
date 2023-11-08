package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2023.
 * #1899 https://leetcode.com/problems/merge-triplets-to-form-target-triplet/
 */
public class MergeTripletsToFormTargetTriplet {

	// time O(n), space O(1)
	public boolean mergeTriplets(int[][] triplets, int[] target) {
		int maxA = target[0], maxB = target[1], maxC = target[2];
		int currA = 0, currB = 0, currC = 0;
		for (int[] t : triplets) {
			if (t[0] > maxA || t[1] > maxB || t[2] > maxC) { // filter out invalid triplet
				continue;
			}
			currA = Math.max(currA, t[0]);
			currB = Math.max(currB, t[1]);
			currC = Math.max(currC, t[2]);
		}
		return currA == maxA && currB == maxB && currC == maxC;
	}
}
