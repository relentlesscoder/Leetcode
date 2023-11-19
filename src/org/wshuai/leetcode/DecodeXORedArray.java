package org.wshuai.leetcode;

/**
 * Created by Wei on 01/13/2021.
 * #1720 https://leetcode.com/problems/decode-xored-array/
 */
public class DecodeXORedArray {

    // time O(n)
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length + 1;
        int[] res = new int[n];
        res[0] = first;
        for(int i = 1; i < n; i++){
            res[i] = encoded[i - 1] ^ res[i - 1];
        }
        return res;
    }
}
