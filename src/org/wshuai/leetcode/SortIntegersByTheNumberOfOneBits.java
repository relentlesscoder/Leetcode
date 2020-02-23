package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 02/22/2020.
 * #1356 https://leetcode.com/problems/sort-integers-by-the-number-of-1-bits/
 */
public class SortIntegersByTheNumberOfOneBits {
	// time O(n*log(n))
	public int[] sortByBits(int[] arr) {
		int n = arr.length;
		int[][] vals = new int[n][2];
		for(int i = 0; i < n; i++){
			vals[i] = new int[]{arr[i], countOnes(arr[i])};
		}
		Arrays.sort(vals, (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
		int i = 0;
		for(int[] v : vals){
			arr[i++] = v[0];
		}
		return arr;
	}

	private int countOnes(int num){
		int mask1 = 0x55555555, mask2 = 0x33333333,
			mask3 = 0x0f0f0f0f, mask4 = 0x00ff00ff,
			mask5 = 0x0000ffff;
		int res = ((num >> 1) & mask1) + (num & mask1);
		res = ((res >> 2) & mask2) + (res & mask2);;
		res = ((res >> 4) & mask3) + (res & mask3);;
		res = ((res >> 8) & mask4) + (res & mask4);;
		res = ((res >> 16) & mask5) + (res & mask5);;
		return res;
	}
}
