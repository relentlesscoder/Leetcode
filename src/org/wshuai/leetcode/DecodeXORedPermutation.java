package org.wshuai.leetcode;

/**
 * Created by Wei on 01/06/2024.
 * #1734 https://leetcode.com/problems/decode-xored-permutation/
 */
public class DecodeXORedPermutation {

    // time O(n), space O(n)
    public int[] decode(int[] encoded) {
        // Same idea as in #1720 https://leetcode.com/problems/decode-xored-array/ to get the first element.
        // For example, original array is [a,b,c,d,e] and the encoded array is [a^b, b^c, c^d, d^e]
        // We can xor all odd positions with the permutation to get the first element: a = (b^c)^(d^e)^(a^b^c^d^e)
        int n = encoded.length, xor = n + 1;
        int[] res = new int[n + 1];
        for (int i = 0; i < n; i++) {
            xor ^= (i + 1);
            if (i % 2 == 1) {
                xor ^= encoded[i];
            }
        }
        res[0] = xor;
        for (int i = 0; i < n; i++) {
            res[i + 1] = res[i] ^ encoded[i];
        }
        return res;
    }
}
