package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/27/2025.
 * #3164 https://leetcode.com/problems/find-the-number-of-good-pairs-ii/
 */
public class FindTheNumberOfGoodPairsII {

    // time O(n * sqrt(max / k) + m), space O(max / k)
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        long res = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums1) {
            if (num % k != 0) {
                continue;
            }
            // find frequency of all factors of each num / k
            num /= k;
            for (int i = 1; i * i <= num; i++) {
                if (num % i != 0) {
                    continue;
                }
                freq.put(i, freq.getOrDefault(i, 0) + 1);
                if (i * i < num) { // exclude i * i == num to avoid duplicate
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
