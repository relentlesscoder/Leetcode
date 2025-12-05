package org.wshuai.leetcode;

/**
 * Created by Wei on 07/05/2025.
 * #2429 https://leetcode.com/problems/minimize-xor/
 */
public class MinimizeXOR {

    // time O(n), space O(1)
    public int minimizeXor(int num1, int num2) {
        int res = 0, setBitsCount = 0;
        // count set bits of num2
        for (int i = 0; i < 30; i++) {
            if ((num2 & 1) == 1) {
                setBitsCount++;
            }
            num2 = num2 >> 1;
        }
        // set bits to 1 to match set bits in num1 from most
        // significant bits
        for (int i = 29; i >= 0 && setBitsCount > 0; i--) {
            if ((1 << i & num1) > 0) {
                res = res | (1 << i);
                setBitsCount--;
            }
        }
        // if there are still set bits left, fill the rest from
        // the least significant bits
        for (int i = 0; i < 30 && setBitsCount > 0; i++) {
            if ((1 << i & res) == 0) {
                res = res | (1 << i);
                setBitsCount--;
            }
        }
        return res;
    }
}
