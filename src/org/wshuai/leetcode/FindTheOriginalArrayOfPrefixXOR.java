package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/2023.
 * #2433 https://leetcode.com/problems/find-the-original-array-of-prefix-xor/
 */
public class FindTheOriginalArrayOfPrefixXOR {

    // time O(n), space O(1)
    public int[] findArray(int[] pref) {
        int n = pref.length;
        // Since pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i]
        //       pref[i + 1] = arr[0] ^ arr[1] ^ ... ^ arr[i] ^ arr[i + 1]
        // thus arr[i + 1] = pref[i + 1] ^ pref[i]
        for (int i = n - 1; i > 0; i--) {
            pref[i] ^= pref[i - 1];
        }
        return pref;
    }
}
