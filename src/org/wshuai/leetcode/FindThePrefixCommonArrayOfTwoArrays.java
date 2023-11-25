package org.wshuai.leetcode;

/**
 * Created by Wei on 11/25/2023.
 * #2657 https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/
 */
public class FindThePrefixCommonArrayOfTwoArrays {

    // time O(n), space O(n)
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length, prevCommons = 0;
        int[] res = new int[n], numA = new int[51], numB = new int[51];
        for (int i = 0; i < n; i++) {
            int a = A[i], b = B[i];
            res[i] += prevCommons;
            if (a == b) {
                res[i]++;
            } else {
                res[i] += numB[a] > 0 ? 1 : 0;
                res[i] += numA[b] > 0 ? 1 : 0;
            }
            numA[a]++;
            numB[b]++;
            prevCommons = res[i];
        }
        return res;
    }
}
