package org.wshuai.leetcode;

/**
 * Created by Wei on 01/05/2020.
 * #1310 https://leetcode.com/problems/xor-queries-of-a-subarray/
 */
public class XORQueriesOfASubarray {

    // time O(n), space O(n)
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length, m = queries.length;
        int[] res = new int[m], prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] ^ arr[i];
        }
        for (int i = 0; i < m; i++) {
            int start = queries[i][0], end = queries[i][1];
            res[i] = prefix[end + 1] ^ prefix[start];
        }
        return res;
    }
}
