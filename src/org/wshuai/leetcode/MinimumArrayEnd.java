package org.wshuai.leetcode;

/**
 * Created by Wei on 09/21/2025.
 * #3133 https://leetcode.com/problems/minimum-array-end/
 */
public class MinimumArrayEnd {

    // time O(log(n)), space O(1)
    public long minEndBitMask(int n, int x) {
        long res = x;
        n--;
        // Step 1: Iterate over each bit position with mask starting at 1 and shifting left
        for (long mask = 1; n > 0; mask <<= 1) {
            // Step 2: If the corresponding bit in x is 0
            if ((mask & x) == 0) {
                // Set the bit in result based on the least significant bit of n
                res |= (n & 1) * mask;
                // Shift n to the right by 1 to process the next bit
                n >>= 1;
            }
        }
        return res;
    }

    // time O(log(n)), space O(log(n))
    public long minEndBinaryConstruction(int n, int x) {
        long res = 0, bit = 0, longX = x, longN = n - 1;
        int posX = 0, posN = 0;
        // Step 1: Initialize vectors to hold the binary representation of x and n-1
        int[] binaryX = new int[64], binaryN = new int[64];
        // Step 2: Build binary representations of x and n-1
        for (int i = 0; i < 64; i++) {
            bit = (longX >> i) & 1;
            binaryX[i] = (int) bit;
            bit = (longN >> i) & 1;
            binaryN[i] = (int) bit;
        }
        // Step 3: Combine binary representation of x and n-1
        while (posX < 64) {
            // Traverse binaryX until we find a 0 bit
            while (binaryX[posX] != 0 && posX < 64) {
                posX++;
            }
            // Copy bits from binaryN (n-1) into binaryX (x) starting from the first 0
            binaryX[posX] = binaryN[posN];
            ++posX;
            ++posN;
        }
        // Step 4: Rebuild the final result from the combined binary representation
        for (int i = 0; i < 64; i++) {
            if (binaryX[i] == 1) {
                res += (1L << i);
            }
        }
        return res;
    }

    // time O(n), space O(1)
    public long minEndLinearEnumeration(int n, int x) {
        long res = x;
        while (--n > 0) {
            res = (res + 1) | x;
        }
        return res;
    }
}

/**
 * Input: n = 3, x = 4
 * 12 -> 1100
 * 7  -> 111
 * 6  -> 110
 * 5  -> 101
 * 4  -> 100
 *
 * Input: n = 2, x = 7
 * 15 -> 1111
 * 7  -> 111
 */
