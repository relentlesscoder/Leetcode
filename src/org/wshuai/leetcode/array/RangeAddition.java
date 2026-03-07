package org.wshuai.leetcode.array;

/**
 * Created by Wei on 10/31/2016.
 * #0370 https://leetcode.com/problems/range-addition/
 */
public class RangeAddition {

    // time O(n), space O(n)
    public int[] getModifiedArray(int length, int[][] updates) {
		// 差分数组应用
        int[] res = new int[length], diff = new int[length + 1];
        for (int[] u : updates) {
            diff[u[0]] += u[2];
            diff[u[1] + 1] -= u[2];
        }
        for (int i = 0, sum = 0; i < length; i++) {
            sum += diff[i];
            res[i] = sum;
        }
        return res;
    }
}
