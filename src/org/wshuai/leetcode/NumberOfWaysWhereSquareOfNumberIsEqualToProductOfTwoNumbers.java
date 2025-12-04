package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 09/06/2020.
 * #1577 https://leetcode.com/problems/number-of-ways-where-square-of-number-is-equal-to-product-of-two-numbers/
 */
public class NumberOfWaysWhereSquareOfNumberIsEqualToProductOfTwoNumbers {

    // time O(m * log(m) + n * log(n) + m * n), space O(log(m) + log(n))
    public int numTripletsTwoPointers(int[] nums1, int[] nums2) {
        return calc(nums1, nums2) + calc(nums2, nums1);
    }

    private int calc(int[] nums1, int[] nums2) {
        Arrays.sort(nums2);
        int res = 0, m = nums1.length, n = nums2.length;
        for (int k = 0; k < m; k++) {
            long square = (long) nums1[k] * nums1[k];
            for (int i = 0, j = n - 1; i < j; ) {
                long prod = (long) nums2[i] * nums2[j];
                if (prod == square) {
                    if (nums2[i] == nums2[j]) {
                        res += (j - i) * (j - i + 1) / 2;
                        break;
                    } else {
                        int c1 = 0, c2 = 0, v1 = nums2[i], v2 = nums2[j];
                        while (nums2[i] == v1) {
                            c1++;
                            i++;
                        }
                        while (nums2[j] == v2) {
                            c2++;
                            j--;
                        }
                        res += c1 * c2;
                    }
                } else if (prod < square) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return res;
    }

    // time O(m*n), space O(m + n)
    public int numTriplets(int[] nums1, int[] nums2) {
        int res = 0;
        for (int num : nums1) {
            res += countPairs((long) num * num, nums2);
        }
        for (int num : nums2) {
            res += countPairs((long) num * num, nums1);
        }
        return res;
    }

    private int countPairs(long target, int[] nums) {
        int res = 0;
        Map<Long, Integer> freq = new HashMap<>();
        for (int num : nums) {
            if (target % num != 0) {
                continue;
            }
            res += freq.getOrDefault(target / num, 0);
            freq.merge((long) num, 1, Integer::sum);
        }
        return res;
    }
}
