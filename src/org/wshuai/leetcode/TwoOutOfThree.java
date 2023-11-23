package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/22/2023.
 * #2032 https://leetcode.com/problems/two-out-of-three/
 */
public class TwoOutOfThree {

    // time O(n), space O(1)
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        List<Integer> res = new ArrayList<>();
        int[] counter = new int[101];
        count(nums1, counter, 0);
        count(nums2, counter, 1);
        count(nums3, counter, 2);
        for (int i = 1; i <= 100; i++) {
            int bits = 0;
            for (int j = 0; j < 3; j++) {
                bits += ((counter[i] >> j) & 1);
            }
            if (bits >= 2) {
                res.add(i);
            }
        }
        return res;
    }

    private void count(int[] nums, int[] counter, int bit) {
        for (int n : nums) {
            counter[n] |= (1 << bit);
        }
    }
}
