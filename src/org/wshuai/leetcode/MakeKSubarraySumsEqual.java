package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 09/07/2023.
 * #2607 https://leetcode.com/problems/make-k-subarray-sums-equal/
 */
public class MakeKSubarraySumsEqual {

    // time O(log(n/k) * (n/k) * k), space O(n)
    public long makeSubKSumEqual(int[] arr, int k) {
        long res = 0;
        for (int i = 0; i < k; i++) {
            List<Integer> cycle = new ArrayList<>();
            for (int j = i; arr[j] != 0; j = (j + k) % arr.length) {
                cycle.add(arr[j]);
                arr[j] = 0;
            }
            Collections.sort(cycle);
            for (int n : cycle) {
                res += Math.abs(n - cycle.get(cycle.size() / 2));
            }
        }
        return res;
    }
}
