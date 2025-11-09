package org.wshuai.leetcode;

/**
 * Created by Wei on 11/09/2025.
 * #2028 https://leetcode.com/problems/find-missing-observations/
 */
public class FindMissingObservations {

    // time O(m + n), space O(1)
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length, sum = (m + n) * mean;
        int[] res = new int[n];
        for (int r : rolls) {
            sum -= r;
        }
        if (sum < n) {
            return new int[0];
        }
        int val = sum / n, rem = sum % n;
        for (int i = 0; i < n; i++) {
            res[i] = val + (rem-- > 0 ? 1 : 0);
            if (res[i] > 6) {
                return new int[0];
            }
        }
        return res;
    }
}
