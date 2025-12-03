package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 06/07/2020.
 * #1471 https://leetcode.com/problems/the-k-strongest-values-in-an-array/
 */
public class TheKStrongestValuesInAnArray {

    // time O(n * log(n)), space O(k)
    public int[] getStrongest(int[] arr, int k) {
        int n = arr.length;
        int[] res = new int[k];
        Arrays.sort(arr);
        int centre = arr[(n - 1) / 2];
        for (int i = 0, j = n - 1; i <= j && k > 0; k--) {
            int s1 = centre - arr[i], s2 = arr[j] - centre;
            if (s2 >= s1) {
                res[k - 1] = arr[j--];
            } else {
                res[k - 1] = arr[i++];
            }
        }
        return res;
    }
}
