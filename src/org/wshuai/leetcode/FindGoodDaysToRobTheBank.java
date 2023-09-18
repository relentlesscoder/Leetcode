package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/17/2023.
 * #2100 https://leetcode.com/problems/find-good-days-to-rob-the-bank/
 */
public class FindGoodDaysToRobTheBank {

    // time O(n), space O(n)
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> res = new ArrayList<>();
        int n = security.length;
        int[] prefixSum = new int[n];
        // use prefix sum to find the number of non-increasing elements from left for each index
        for (int i = 1; i < n; i++) {
            if (security[i - 1] >= security[i]) {
                prefixSum[i] = prefixSum[i - 1] + 1;
            }
        }
        int curr = 0; // scan from the right to find number of non-increasing for each index
        for (int i = n - 1; i >= 0; i--) {
            if (i + 1 < n && security[i + 1] >= security[i]) {
                curr++;
            } else {
                curr = 0;
            }
            if (curr >= time && prefixSum[i] >= time) {
                res.add(i);
            }
        }
        return res;
    }
}
