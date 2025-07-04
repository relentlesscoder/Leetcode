package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 07/04/2025.
 * #2425 https://leetcode.com/problems/bitwise-xor-of-all-pairings/
 */
public class BitwiseXOROfAllPairings {

    // time O(m + n), space O(1)
    public int xorAllNums(int[] nums1, int[] nums2) {
        // a ^ a = 0, a ^ 0 = a
        int xor1 = 0, xor2 = 0, len1 = nums1.length, len2 = nums2.length;
        Map<Integer, Integer> freq = new HashMap<>();
        if (len2 % 2 == 1) {
            for (int num : nums1) {
                xor1 ^= num;
            }
        }
        if (len1 % 2 == 1) {
            for (int num : nums2) {
                xor2 ^= num;
            }
        }
        return xor1 ^ xor2;
    }

    // time O(m + n), space O(m + n)
    public int xorAllNumsHashMap(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums1) {
            freq.put(num, freq.getOrDefault(num, 0) + len2);
        }
        for (int num : nums2) {
            freq.put(num, freq.getOrDefault(num, 0) + len1);
        }
        int res = 0;
        for (int num : freq.keySet()) {
            if (freq.get(num) % 2 == 1) {
                res ^= num;
            }
        }
        return res;
    }
}
