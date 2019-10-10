package org.wshuai.leetcode;

/**
 * Created by Wei on 8/10/19.
 * #821 https://leetcode.com/problems/shortest-distance-to-a-character/
 */
public class ShortestDistanceToACharacter {
	public int[] shortestToChar(String S, char C) {
		int[] res = new int[S.length()];
		for (int i = 0; i < res.length; i++) {
			res[i] = Integer.MAX_VALUE;
		}
		char[] arr = S.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != C) {
				continue;
			}
			res[i] = 0;
			int j = i - 1;
			while (j >= 0 && arr[j] != C) {
				int dis = i - j;
				if (res[j] > dis) {
					res[j] = dis;
				} else {
					break;
				}
				j--;
			}
			int k = i + 1;
			while (k < res.length && arr[k] != C) {
				int dis = k - i;
				if (res[k] > dis) {
					res[k] = dis;
				} else {
					break;
				}
				k++;
			}
		}
		return res;
	}
}
