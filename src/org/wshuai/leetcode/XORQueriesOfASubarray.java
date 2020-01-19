package org.wshuai.leetcode;

/**
 * Created by Wei on 01/05/2020.
 * #1310 https://leetcode.com/problems/xor-queries-of-a-subarray/
 */
public class XORQueriesOfASubarray {
	public int[] xorQueries(int[] arr, int[][] queries) {
		int n = arr.length;
		int[] prefix = new int[n + 1];
		for(int i = 1; i <= n; i++){
			prefix[i] = arr[i - 1] ^ prefix[i - 1];
		}
		int[] res = new int[queries.length];
		int i = 0;
		for(int[] q : queries){
			res[i++] = prefix[q[1] + 1] ^ prefix[q[0]];
		}
		return res;
	}
}
