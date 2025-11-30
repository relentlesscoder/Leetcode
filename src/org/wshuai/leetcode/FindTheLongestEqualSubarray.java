package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 11/30/2025.
 * #2831 https://leetcode.com/problems/find-the-longest-equal-subarray/
 */
public class FindTheLongestEqualSubarray {

    // time O(n), space O(n)
    public int longestEqualSubarray(List<Integer> nums, int k) {
        // Use sliding window against index list for each unique numbers
        // in the array to find the longest subarray length of equal
        // subarrays by deleting other numbers
        int res = 0, n = nums.size();
        List<Integer>[] countMap = new ArrayList[n + 1];
        Arrays.setAll(countMap, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            int x = nums.get(i);
            // Save i - countMap[x].size() which will be the prefix sum of
            // count of other numbers than x. For example, original indexes
            // for nums[i] are [1, 3, 6]:
            // indexes: [0, 1, 2, 3, 4, 5, 6, 7]
            // mapping: [_, 1, _, 2, _, _, 4]
            // Where "_" represents numbers other than nums[i], so now
            // mapping.get(i) - mapping.get(j) is the count of numbers we
            // need to delete "jumping" from index j to i (add index i to
            // the current window).
            countMap[x].add(i - countMap[x].size());
        }
        for (List<Integer> counts : countMap) {
            if (counts.size() <= res) {
                continue;
            }
            for (int i = 0, j = 0; i < counts.size(); i++) {
                // If k is not enough, advance j to shorten the current
                // window
                while (counts.get(i) - counts.get(j) > k) {
                    j++;
                }
                res = Math.max(res, i - j + 1);
            }
        }
        return res;
    }
}
