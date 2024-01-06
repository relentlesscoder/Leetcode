package org.wshuai.leetcode;

/**
 * Created by Wei on 01/13/2021.
 * #1720 https://leetcode.com/problems/decode-xored-array/
 */
public class DecodeXORedArray {

    // time O(n), space O(n)
    public int[] decode(int[] encoded, int first) {
        // For example, original array is [a,b,c,d,e] and the encoded array is [a^b, b^c, c^d, d^e]
        // we know first element is a then we have:
        // b = a^a^b
        // c = b^b^c
        // d = c^c^d
        // e = d^d^e
        int n = encoded.length;
        int[] res = new int[n + 1];
        res[0] = first;
        for (int i = 0; i < n; i++) {
            res[i + 1] = encoded[i] ^ res[i];
        }
        return res;
    }
}
