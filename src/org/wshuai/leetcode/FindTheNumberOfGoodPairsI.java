package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/27/2025.
 * #3162 https://leetcode.com/problems/find-the-number-of-good-pairs-i/
 */
public class FindTheNumberOfGoodPairsI {

    // time O(m * n), space O(1)
    public int numberOfPairsBrutalForce(int[] nums1, int[] nums2, int k) {
        int res = 0;
        for (int num1 : nums1) {
            if (num1 % k != 0) {
                continue;
            }
            num1 /= k;
            for (int num2 : nums2) {
                if (num1 % num2 != 0) {
                    continue;
                }
                res++;
            }
        }
        return res;
    }

    // time O(n * sqrt(max / k) + m), space O(max / k)
    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        int res = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums1) {
            if (num % k != 0) {
                continue;
            }
            num /= k;
            for (int i = 1; i * i <= num; i++) {
                if (num % i != 0) {
                    continue;
                }
                freq.put(i, freq.getOrDefault(i, 0) + 1);
                if (i * i < num) {
                    freq.put(num / i, freq.getOrDefault(num / i, 0) + 1);
                }
            }
        }
        for (int num : nums2) {
            res += freq.getOrDefault(num, 0);
        }
        return res;
    }
}
