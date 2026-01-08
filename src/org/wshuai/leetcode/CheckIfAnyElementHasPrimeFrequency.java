package org.wshuai.leetcode;

/**
 * Created by Wei on 01/07/2026.
 * #3591 https://leetcode.com/problems/check-if-any-element-has-prime-frequency/
 */
public class CheckIfAnyElementHasPrimeFrequency {

    private static final int MAX = 101;
    private static final boolean[] NON_PRIME = new boolean[MAX];

    static {
        NON_PRIME[0] = NON_PRIME[1] = true;
        for (int i = 2; i < MAX; i++) {
            if (!NON_PRIME[i]) {
                for (int j = i; j <= MAX / i; j++) {
                    NON_PRIME[i * j] = true;
                }
            }
        }
    }

    // time O(n), space O(MAX)
    public boolean checkPrimeFrequency(int[] nums) {
        int[] freq = new int[101];
        for (int num : nums) {
            freq[num]++;
        }
        for (int cnt : freq) {
            if (!NON_PRIME[cnt]) {
                return true;
            }
        }
        return false;
    }
}
