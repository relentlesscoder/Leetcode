package org.wshuai.leetcode;

/**
 * Created by Wei on 11/25/2023.
 * #2317 https://leetcode.com/problems/maximum-xor-after-operations/
 */
public class MaximumXORAfterOperations {

    // time O(n), space O(1)
    public int maximumXOR(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res |= num;
        }
        return res;
    }

    // time O(n), space O(1)
    public int maximumXORSetBit(int[] nums) {
        int res = 0;
        // For each bit position, the best we can do to increase
        // the XOR sum is to covert the even count of set bits (one)
        // to odd. Therefore, the max XOR sum is just the number of
        // all bit position that we see at least one set bit (if there
        // is no set bits at the bit position, we can't do anything).
        for (int i = 0; i < 27; i++) {
            for (int num : nums) {
                if (((num >> i) & 1) == 1) {
                    res += (1 << i);
                    break;
                }
            }
        }
        return res;
    }
}
