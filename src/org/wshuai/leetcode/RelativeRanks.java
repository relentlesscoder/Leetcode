package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Wei on 3/5/17.
 * #506 https://leetcode.com/problems/relative-ranks/
 */
public class RelativeRanks {
	public String[] findRelativeRanks(int[] nums) {
		int len = nums.length;
		int[][] aux = new int[len][2];
		for (int i = 0; i < len; i++) {
			aux[i][0] = nums[i];
			aux[i][1] = i;
		}
		Arrays.sort(aux, new RankComparator());
		String[] res = new String[len];
		int rank = len;
		for (int i = 0; i < len; i++) {
			res[aux[i][1]] = Integer.toString(rank);
			rank--;
		}
		res[aux[len - 1][1]] = "Gold Medal";
		if (len > 1) {
			res[aux[len - 2][1]] = "Silver Medal";
		}
		if (len > 2) {
			res[aux[len - 3][1]] = "Bronze Medal";
		}
		return res;
	}
}

class RankComparator implements Comparator<int[]> {
	@Override
	public int compare(int[] arr1, int[] arr2) {
		return arr1[0] - arr2[0];
	}
}