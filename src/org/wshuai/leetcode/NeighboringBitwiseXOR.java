package org.wshuai.leetcode;

/**
 * Created by Wei on 06/22/2025.
 * #2683 https://leetcode.com/problems/neighboring-bitwise-xor/
 */
public class NeighboringBitwiseXOR {

    // time O(n), space O(1)
    public boolean doesValidArrayExist(int[] derived) {
        int current = 0, n = derived.length;
        for (int i = 0; i < n - 1; i++) {
            current = current ^ derived[i];
        }
        return (current ^ 0) == derived[n - 1];
    }
}
